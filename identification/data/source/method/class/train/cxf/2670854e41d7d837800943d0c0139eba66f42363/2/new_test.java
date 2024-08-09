    @Test
    public void testmakeNamespaceFromClassName() throws Exception {
        String tns = ServiceUtils.makeNamespaceFromClassName("com.example.ws.Test", "http");
        assertEquals("http://ws.example.com/", tns);
    }