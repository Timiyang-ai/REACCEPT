@Test
    public void testCreate_OptionalGetter() {
        Map<String, Object> values = Maps.newHashMap();
        values.put("name", Optional.ofNullable("MyName"));

        ClassGeneratorProvider provider = createProvider();
        EventFactory<OptionalGetter> factory = provider.create(OptionalGetter.class, Object.class, SpongeEventFactoryUtils.plugins);
        OptionalGetter getter = factory.apply(values);

        assertThat(getter.getName().isPresent(), is(true));
        assertThat(getter.getName().get(), is(Matchers.equalTo("MyName")));

        getter.setName(null);
        assertThat(getter.getName().isPresent(), is(false));

        getter.setName("Aaron");

        assertThat(getter.getName().isPresent(), is(true));
        assertThat(getter.getName().get(), is(Matchers.equalTo("Aaron")));
    }