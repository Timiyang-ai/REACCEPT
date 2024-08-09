@Test
    public void testParseQuery() throws Exception {
        String searchString = "product:(resteasy) AND vendor:(red hat)";

        String expResult = "+product:resteasy +(vendor:red vendor:redhat vendor:hat)";
        Query result = instance.parseQuery(searchString);
        assertEquals(expResult, result.toString());
        instance.resetAnalyzers();
        searchString = "product:(struts2\\-core^2 struts^3 core) AND vendor:(apache.struts apache^3 foundation)";

        expResult = "+((product:struts product:strutsstruts2 product:struts2 product:struts2core product:core)^2.0 (product:corestruts product:struts)^3.0 (product:strutscore product:core)) +((vendor:apache vendor:apachestruts vendor:struts) (vendor:strutsapache vendor:apache)^3.0)";
        result = instance.parseQuery(searchString);
        assertEquals(expResult, result.toString());
        instance.close();
    }