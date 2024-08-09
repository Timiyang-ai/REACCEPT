    @Test
    void tagKey() {
        assertThat(convention.tagKey("123abc/{:id}水")).isEqualTo("123abc___id__");
    }