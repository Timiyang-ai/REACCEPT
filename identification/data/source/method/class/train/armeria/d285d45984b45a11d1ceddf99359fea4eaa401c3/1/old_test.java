    @Test
    void parse() {
        final Endpoint foo = Endpoint.parse("foo");
        assertThat(foo).isEqualTo(Endpoint.of("foo"));
        assertThatThrownBy(foo::port).isInstanceOf(IllegalStateException.class);
        assertThat(foo.weight()).isEqualTo(1000);
        assertThat(foo.ipAddr()).isNull();
        assertThat(foo.ipFamily()).isNull();
        assertThat(foo.hasIpAddr()).isFalse();
        assertThat(foo.hasPort()).isFalse();
        assertThat(foo.toUri("none+http").toString()).isEqualTo("none+http://foo");

        final Endpoint bar = Endpoint.parse("bar:80");
        assertThat(bar).isEqualTo(Endpoint.of("bar", 80));
        assertThat(bar.port()).isEqualTo(80);
        assertThat(bar.weight()).isEqualTo(1000);
        assertThat(bar.ipAddr()).isNull();
        assertThat(bar.ipFamily()).isNull();
        assertThat(bar.hasIpAddr()).isFalse();
        assertThat(bar.hasPort()).isTrue();
        assertThat(bar.toUri("none+http").toString()).isEqualTo("none+http://bar:80");

        assertThat(Endpoint.parse("group:foo")).isEqualTo(Endpoint.ofGroup("foo"));
    }