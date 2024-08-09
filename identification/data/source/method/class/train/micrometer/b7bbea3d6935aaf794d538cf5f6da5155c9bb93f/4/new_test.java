    @Test
    void stream() {
        Tags tags = Tags.of(Tag.of("k1",  "v1"), Tag.of("k1",  "v1"), Tag.of("k2", "v2"));
        assertThat(tags.stream()).hasSize(2);
    }