    @Test
    public void build() throws Exception {
        final Retrofit retrofit = new ArmeriaRetrofitBuilder().baseUrl("http://example.com:8080/").build();
        assertThat(retrofit.baseUrl().toString()).isEqualTo("http://example.com:8080/");
    }