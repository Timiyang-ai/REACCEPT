@Test
    public void testSuppressHostByProvider() throws Exception {
        Set<String> supprsuppressHostByProvider = config.suppressHostByProvider();
        assertNotNull("suppressHostByProvider should not be null", supprsuppressHostByProvider);
        assertThat(supprsuppressHostByProvider.size(), is(2));
        assertTrue(supprsuppressHostByProvider.contains(PROVIDER_1));
        assertTrue(supprsuppressHostByProvider.contains(PROVIDER_2));
    }