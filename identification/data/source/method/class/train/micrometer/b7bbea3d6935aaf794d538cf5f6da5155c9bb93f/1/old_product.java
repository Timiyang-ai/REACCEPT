static MeterFilter ignoreTags(String... tagKeys) {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                List<Tag> tags = stream(id.getTags().spliterator(), false)
                        .filter(t -> {
                            for (String tagKey : tagKeys) {
                                if (t.getKey().equals(tagKey))
                                    return false;
                            }
                            return true;
                        }).collect(Collectors.toList());

                return new Meter.Id(id.getName(), tags, id.getBaseUnit(), id.getDescription(), id.getType());
            }
        };
    }