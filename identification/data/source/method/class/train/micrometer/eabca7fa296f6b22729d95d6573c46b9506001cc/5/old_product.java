static MeterFilter maximumAllowableTags(String meterNamePrefix, String tagKey, int maximumTagValues,
                                            MeterFilter onMaxReached) {
        return new MeterFilter() {
            private final Set<String> observedTagValues = new ConcurrentSkipListSet<>();

            @Override
            public MeterFilterReply accept(Meter.Id id) {
                String value = getTagValue(id);
                if (value != null) {
                    if (!observedTagValues.contains(value)) {
                        if (observedTagValues.size() >= maximumTagValues) {
                            return onMaxReached.accept(id);
                        }
                        observedTagValues.add(value);
                    }
                }
                return MeterFilterReply.NEUTRAL;
            }

            private String getTagValue(Meter.Id id) {
                return (id.getName().startsWith(meterNamePrefix) ? id.getTag(tagKey) : null);
            }

            @Override
            public DistributionStatisticConfig configure(Meter.Id id, DistributionStatisticConfig config) {
                String value = getTagValue(id);
                if (value != null) {
                    if (!observedTagValues.contains(value)) {
                        if (observedTagValues.size() >= maximumTagValues) {
                            return onMaxReached.configure(id, config);
                        }
                    }
                }
                return config;
            }
        };
    }