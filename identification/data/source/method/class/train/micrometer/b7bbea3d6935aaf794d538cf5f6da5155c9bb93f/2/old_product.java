static MeterFilter replaceTagValues(String tagKey, Function<String, String> replacement, String... exceptions) {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                List<Tag> tags = stream(id.getTags().spliterator(), false)
                        .map(t -> {
                            if (!t.getKey().equals(tagKey))
                                return t;
                            for (String exception : exceptions) {
                                if (t.getValue().equals(exception))
                                    return t;
                            }
                            return Tag.of(tagKey, replacement.apply(t.getValue()));
                        })
                        .collect(Collectors.toList());

                return new Meter.Id(id.getName(), tags, id.getBaseUnit(), id.getDescription(), id.getType());
            }
        };
    }