package com.web.mlog_server.mvc.series;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class SeriesDto {
    @Data
    @NoArgsConstructor
    public static class SeriesCommonDto {
        private String series;

        @Builder
        public SeriesCommonDto(String series) {
            this.series = series;
        }
    }
    @Data
    @NoArgsConstructor
    public static class SeriesChangeDto {
        private String originalSeries;
        private String newSeries;

        @Builder
        public SeriesChangeDto(String originalSeries, String newSeries) {
            this.originalSeries = originalSeries;
            this.newSeries = newSeries;
        }
    }
}
