@Test
    public void testCreate_OptionalGetter() {
        OptionalGetter getter = this.getFactory().createOptionalGetter(Optional.ofNullable("MyName"));

        assertThat(getter.getName().isPresent(), is(true));
        assertThat(getter.getName().get(), is(equalTo("MyName")));

        getter.setName(null);
        assertThat(getter.getName().isPresent(), is(false));

        getter.setName("Aaron");

        assertThat(getter.getName().isPresent(), is(true));
        assertThat(getter.getName().get(), is(equalTo("Aaron")));
    }