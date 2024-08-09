@Test
    public void testReadPom_File() throws Exception {
        File file = BaseTest.getResourceAsFile(this, "dwr-pom.xml");
        String expResult = "Direct Web Remoting";
        Model result = PomUtils.readPom(file);
        assertEquals(expResult, result.getName());

        expResult = "get ahead";
        assertEquals(expResult, result.getOrganization());
        expResult = "http://getahead.ltd.uk/dwr";
        assertEquals(expResult, result.getOrganizationUrl());

        file = BaseTest.getResourceAsFile(this, "jmockit-1.26.pom");
        expResult = "Main";
        result = PomUtils.readPom(file);
        assertEquals(expResult, result.getName());
    }