    @Test
    void ignoreTags() {
        MeterFilter filter = MeterFilter.ignoreTags("k1", "k2");
        Meter.Id id = new Meter.Id("name", Tags.of("k1", "v1", "k2", "v2", "k3", "v3"), null, null, Meter.Type.COUNTER);

        Meter.Id filteredId = filter.map(id);
        assertThat(filteredId).has(tag("k3"));
        assertThat(filteredId).doesNotHave(tag("k1"));
        assertThat(filteredId).doesNotHave(tag("k2"));
    }