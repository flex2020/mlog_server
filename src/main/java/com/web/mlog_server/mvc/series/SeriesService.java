package com.web.mlog_server.mvc.series;

import com.web.mlog_server.mvc.series.model.PostSeries;
import com.web.mlog_server.mvc.series.model.PostSeriesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SeriesService {
    private final PostSeriesRepository postSeriesRepository;

    public List<SeriesDto.SeriesCommonDto> getSeriesList() {
        return postSeriesRepository.findAll()
                .stream()
                .map(PostSeries::toCommonDto)
                .toList();
    }
    public Boolean addSeries(String series) {
        if (postSeriesRepository.existsBySeries(series)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        PostSeries postSeries = PostSeries.builder()
                .series(series)
                .build();
        try {
            postSeriesRepository.save(postSeries);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return true;
    }
    public Boolean deleteSeries(String series) {
        PostSeries postSeries = postSeriesRepository.findBySeries(series)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            postSeriesRepository.delete(postSeries);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return true;
    }
    @Transactional
    public Boolean changeSeries(SeriesDto.SeriesChangeDto dto) {
        PostSeries postSeries = postSeriesRepository.findBySeries(dto.getOriginalSeries())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        try {
            postSeries.setSeries(dto.getNewSeries());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return true;
    }
}
