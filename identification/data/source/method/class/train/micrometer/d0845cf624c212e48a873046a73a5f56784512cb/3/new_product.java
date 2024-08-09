void addMetric(Stream.Builder<WavefrontMetricLineData> metrics, Meter.Id id, @Nullable String suffix,
                   long wallTime, double value) {
        if (!Double.isFinite(value)) {
            return;
        }

        Meter.Id fullId = id;
        if (suffix != null) {
            fullId = idWithSuffix(id, suffix);
        }

        String name = getConventionName(fullId);
        String source = config.source();
        Map<String, String> tags = getTagsAsMap(id);

        try {
            String lineData = Utils.metricToLineData(name, value, wallTime, source, tags, "unknown");
            metrics.add(new WavefrontMetricLineData(lineData, false));
        } catch (IllegalArgumentException e) {
            logger.error("failed to convert metric to Wavefront format: " + fullId.getName(), e);
        }
    }