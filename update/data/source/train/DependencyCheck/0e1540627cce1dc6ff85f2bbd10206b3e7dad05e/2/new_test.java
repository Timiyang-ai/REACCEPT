@Test
    public void testEquals() throws CpeValidationException {
        VulnerableSoftwareBuilder builder = new VulnerableSoftwareBuilder();
        VulnerableSoftware obj = null;
        VulnerableSoftware instance = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1").build();
        assertFalse(instance.equals(obj));

        obj = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1.0").build();
        instance = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1").build();
        assertFalse(instance.equals(obj));

        obj = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1.0").build();
        instance = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1.0").build();
        assertTrue(instance.equals(obj));
    }