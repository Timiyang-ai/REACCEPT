@Test
    public void testParseQuery() throws Exception {
        String searchString = "product:(resteasy) AND vendor:(red hat)";

        String expResult = "+product:resteasy +(vendor:red vendor:redhat vendor:hat)";
        Query result = instance.parseQuery(searchString);
        assertEquals(expResult, result.toString());
        instance.close();
    }