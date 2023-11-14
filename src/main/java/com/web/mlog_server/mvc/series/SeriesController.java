package com.web.mlog_server.mvc.series;

import com.web.mlog_server.mvc.post.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/series")
@Slf4j
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;
    @GetMapping("")
    public List<SeriesDto.SeriesCommonDto> getSeriesList() {
        return seriesService.getSeriesList();
    }
    @PostMapping("")
    public Boolean addSeries(@RequestBody SeriesDto.SeriesCommonDto dto) {
        return seriesService.addSeries(dto.getSeries());
    }
    @DeleteMapping("/{series}")
    public Boolean deleteSeries(@PathVariable String series) {
        return seriesService.deleteSeries(series);
    }
    @PutMapping("")
    public Boolean changeSeries(@RequestBody SeriesDto.SeriesChangeDto dto) {
        return seriesService.changeSeries(dto);
    }

}
