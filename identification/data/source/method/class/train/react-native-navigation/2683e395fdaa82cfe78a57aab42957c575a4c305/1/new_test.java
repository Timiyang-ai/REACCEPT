    @Test
    public void getDeclaredField() throws Exception {
        assertThat(ReflectionUtils.getDeclaredField(new Foo(), "bar")).isEqualTo("old value");
    }