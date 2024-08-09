private Stream<MetricDatum> metricData(Meter m) {
        long wallTime = clock.wallTime();
        return stream(m.measure().spliterator(), false)
            .map(ms -> metricDatum(m.getId().withTag(ms.getStatistic()), wallTime, ms.getValue()));
    }