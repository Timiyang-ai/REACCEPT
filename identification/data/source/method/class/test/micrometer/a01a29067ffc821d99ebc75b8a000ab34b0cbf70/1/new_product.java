List<MetricDatum> metricData() {
        Batch batch = new Batch();
        return getMeters().stream().flatMap(m -> m.apply(
                batch::gaugeData,
                batch::counterData,
                batch::timerData,
                batch::summaryData,
                batch::longTaskTimerData,
                batch::timeGaugeData,
                batch::functionCounterData,
                batch::functionTimerData,
                batch::metricData)
        ).collect(toList());
    }