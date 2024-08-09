    @Test
    void name() {
        assertThat(convention.name("123abc/{:id}水", Meter.Type.GAUGE)).isEqualTo("123abc/__id__");
    }