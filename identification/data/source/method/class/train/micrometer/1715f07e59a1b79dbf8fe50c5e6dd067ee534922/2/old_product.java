static MeterFilter commonTags(Iterable<Tag> tags) {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                List<Tag> allTags = new ArrayList<>();
                id.getTags().forEach(allTags::add);
                tags.forEach(allTags::add);
                return new Meter.Id(id.getName(), allTags, id.getBaseUnit(), id.getDescription(), id.getType());
            }
        };
    }