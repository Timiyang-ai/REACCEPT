static MeterFilter maximumAllowableTags(String meterNamePrefix, String tagKey, int maximumTagValues,
                                            MeterFilter onMaxReached) {
        return new MeterFilter() {
            private final Set<String> observedTagValues = new ConcurrentSkipListSet<>();

            @Override
            public MeterFilterReply accept(Meter.Id id) {
                if (id.getName().equals(meterNamePrefix)) {
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
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                if (observedTagValues.size() > maximumTagValues) {
                    return onMaxReached.configure(id, config);
                }
                return config;
            }
        };
    }