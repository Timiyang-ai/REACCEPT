    @Test
    void tagKey() {
        assertThat(convention.tagKey("_boo")).isEqualTo("boo");
        assertThat(convention.tagKey("sf_boo")).isEqualTo("boo");

        assertThat(convention.tagKey("123")).isEqualTo("a123");
    }