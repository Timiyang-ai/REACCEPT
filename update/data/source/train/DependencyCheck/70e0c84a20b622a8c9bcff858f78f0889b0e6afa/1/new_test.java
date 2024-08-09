@Test
    public void testReadPom_File() throws Exception {
        File file = BaseTest.getResourceAsFile(this, "dwr-pom.xml");
        String expResult = "Direct Web Remoting";
        Model result = PomUtils.readPom(file);
        assertEquals(expResult, result.getName());
    }