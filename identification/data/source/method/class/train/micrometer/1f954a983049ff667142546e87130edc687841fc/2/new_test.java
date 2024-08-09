    @Test
    void tagValue() {
        assertThat(convention.tagValue("123abc/\"{:id}水\\")).isEqualTo("123abc/\\\"{:id}水_");
        assertThat(convention.tagValue("\\")).isEqualTo("_");
    }