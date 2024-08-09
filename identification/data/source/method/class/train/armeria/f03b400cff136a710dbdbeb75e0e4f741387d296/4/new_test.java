    @Test
    void decoratorTest() throws Exception {
        final WebClient client = WebClient.of(server.uri("/"));
        final AggregatedHttpResponse res = client.get("/").aggregate().get();
        assertThat(res.headers().get("global_decorator")).isEqualTo("true");
        assertThat(res.headers().contains("virtualhost_decorator")).isEqualTo(false);
        final AggregatedHttpResponse res2 = client.get("/test").aggregate().get();
        assertThat(res2.headers().get("global_decorator")).isEqualTo("true");
        assertThat(res2.headers().contains("virtualhost_decorator")).isEqualTo(false);

        final WebClient vhostClient = WebClient.of(clientFactory,
                                                   "http://test.example.com:" + server.httpPort());
        final AggregatedHttpResponse res3 = vhostClient.get("/").aggregate().get();
        assertThat(res3.headers().get("global_decorator")).isEqualTo("true");
        assertThat(res3.headers().get("virtualhost_decorator")).isEqualTo("true");
    }