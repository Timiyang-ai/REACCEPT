static MeterFilter replaceTagValues(String tagKey, Function<String, String> replacement, String... exceptions) {
        return new MeterFilter() {
            @Override
            public Meter.Id map(Meter.Id id) {
                List<Tag> tags = stream(id.getTagsAsIterable().spliterator(), false)
                        .map(t -> {
                            if (!t.getKey().equals(tagKey))
                                return t;
                            for (String exception : exceptions) {
                                if (t.getValue().equals(exception))
                                    return t;
                            }
                            return Tag.of(tagKey, replacement.apply(t.getValue()));
                        })
                        .collect(toList());

                return id.replaceTags(tags);
            }
        };
    }