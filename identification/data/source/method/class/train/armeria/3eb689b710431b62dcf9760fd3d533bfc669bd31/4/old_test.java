    @Test
    public void tryParse_null() {
        assertThat(Scheme.tryParse(null)).isEmpty();
    }