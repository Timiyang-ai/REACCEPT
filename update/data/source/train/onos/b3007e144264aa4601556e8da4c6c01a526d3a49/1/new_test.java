@Test
    public void testSetHostLearning() throws Exception {
        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        builder.add(PROVIDER_3);
        config.setSuppressHostByProvider(builder.build());

        Set<String> supprsuppressHostByProvider = config.suppressHostByProvider();
        assertNotNull("suppressHostByProvider should not be null", supprsuppressHostByProvider);
        assertThat(supprsuppressHostByProvider.size(), is(1));
        assertTrue(supprsuppressHostByProvider.contains(PROVIDER_3));
    }