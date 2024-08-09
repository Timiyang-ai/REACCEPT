    @Test
    void replaceTagValues() {
        MeterFilter filter = MeterFilter.replaceTagValues("status", s -> s.charAt(0) + "xx", "200");

        Meter.Id id = new Meter.Id("name", Tags.of("status", "400"), null, null, Meter.Type.COUNTER);
        Meter.Id filteredId = filter.map(id);
        assertThat(filteredId).has(tag("status", "4xx"));

        id = new Meter.Id("name", Tags.of("status", "200"), null, null, Meter.Type.COUNTER);
        filteredId = filter.map(id);
        assertThat(filteredId).has(tag("status", "200"));
    }