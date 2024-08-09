    @Test
    void commonTags() {
        MeterFilter filter = MeterFilter.commonTags(Tags.of("k2", "v2"));
        Meter.Id id = new Meter.Id("name", Tags.of("k1", "v1"), null, null, Meter.Type.COUNTER);

        Meter.Id filteredId = filter.map(id);
        assertThat(filteredId).has(tag("k1", "v1"));
        assertThat(filteredId).has(tag("k2", "v2"));
    }