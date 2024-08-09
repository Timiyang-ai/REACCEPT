    @Test
    public void should_stub() throws Exception {
        given(mock.simpleMethod("foo")).willReturn("bar");

        Assertions.assertThat(mock.simpleMethod("foo")).isEqualTo("bar");
        Assertions.assertThat(mock.simpleMethod("whatever")).isEqualTo(null);
    }