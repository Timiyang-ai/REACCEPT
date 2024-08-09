@Test
    public void testCompareTo() throws CpeValidationException {
        VulnerableSoftwareBuilder builder = new VulnerableSoftwareBuilder();
        VulnerableSoftware obj = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1.0").build();
        VulnerableSoftware instance = builder.part(Part.APPLICATION).vendor("mortbay").product("jetty").version("6.1").build();
        int result = instance.compareTo(obj);
        assertTrue(result<0);
        
        obj = builder.part(Part.APPLICATION).vendor("yahoo").product("toolbar").version("3.1.0.20130813024103").build();
        instance = builder.part(Part.APPLICATION).vendor("yahoo").product("toolbar").version("3.1.0.20130813024104").build();
        result = instance.compareTo(obj);
        assertTrue(result>0);
    }