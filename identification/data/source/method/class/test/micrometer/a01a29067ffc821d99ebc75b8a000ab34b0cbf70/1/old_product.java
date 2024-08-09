private List<MetricDatum> metricData() {
        return getMeters().stream().flatMap(m -> m.apply(
                this::metricData,
                this::metricData,
                this::timerData,
                this::summaryData,
                this::metricData,
                this::metricData,
                this::metricData,
                this::functionTimerData,
                this::metricData)
        ).collect(toList());
    }