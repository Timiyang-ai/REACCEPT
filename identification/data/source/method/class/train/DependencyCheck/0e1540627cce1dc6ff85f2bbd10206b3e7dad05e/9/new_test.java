@Test
    public void testAddVulnerableSoftwareIIdentifier() throws Exception {
        CpeBuilder builder = new CpeBuilder();
        Cpe cpe = builder.part(Part.APPLICATION).vendor("apache").product("struts").version("2.1.2").build();
        CpeIdentifier id = new CpeIdentifier(cpe, Confidence.HIGHEST);

        cpe = builder.part(Part.APPLICATION).vendor("apache").product("struts").version("2.1.2").build();
        CpeIdentifier expResult = new CpeIdentifier(cpe, Confidence.HIGHEST);

        Dependency instance = new Dependency();
        instance.addVulnerableSoftwareIdentifier(id);
        assertEquals(1, instance.getVulnerableSoftwareIdentifiers().size());
        assertTrue("Identifier doesn't contain expected result.", instance.getVulnerableSoftwareIdentifiers().contains(expResult));
    }