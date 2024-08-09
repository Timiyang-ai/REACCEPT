    @Test
    void withDefaultPort() {
        final Endpoint foo = Endpoint.of("foo");
        final Endpoint foo80 = Endpoint.of("foo", 80);
        assertThat(foo.withDefaultPort(80)).isEqualTo(foo80);
        assertThat(foo80.withDefaultPort(80)).isSameAs(foo80);
        assertThatThrownBy(() -> foo.withDefaultPort(0)).isInstanceOf(IllegalArgumentException.class)
                                                        .hasMessageContaining("defaultPort");
    }