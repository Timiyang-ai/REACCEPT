static MeterFilter maximumAllowableTags(String meterName, String tagKey, int maximumTagValues,
                                            MeterFilter onMaxReached) {
        return new MeterFilter() {
            private final Set<String> observedTagValues = new ConcurrentSkipListSet<>();

            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if (id.getName().equals(meterName)) {
                    String value = id.getTag(tagKey);
                    if (value != null)
                        observedTagValues.add(value);
                }

                if (observedTagValues.size() > maximumTagValues) {
                    return onMaxReached.accept(id);
                }
                return MeterFilterReply.NEUTRAL;
            }

            @Override
            public HistogramConfig configure(Meter.Id id, HistogramConfig config) {
                if (observedTagValues.size() > maximumTagValues) {
                    return onMaxReached.configure(id, config);
                }
                return config;
            }
        };
    }