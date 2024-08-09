static MeterFilter commonTags(Iterable<Tag> tags) {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                return id.withTags(Tags.concat(tags, id.getTagsAsIterable()));
            }
        };
    }