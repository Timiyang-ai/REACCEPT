@Test
    public void testDefineClass() throws Exception {
        final String CLASS_NAME = THIS_PACKAGE + ".Foo";
        Lookup lookup = lookup().dropLookupMode(PRIVATE);
        Class<?> clazz = lookup.defineClass(generateClass(CLASS_NAME));

        // test name
        assertEquals(clazz.getName(), CLASS_NAME);

        // test loader/package/protection-domain
        testSameAbode(clazz, lookup.lookupClass());

        // test discoverable
        testDiscoverable(clazz, lookup);

        // attempt defineClass again
        try {
            lookup.defineClass(generateClass(CLASS_NAME));
            assertTrue(false);
        } catch (LinkageError expected) { }
    }