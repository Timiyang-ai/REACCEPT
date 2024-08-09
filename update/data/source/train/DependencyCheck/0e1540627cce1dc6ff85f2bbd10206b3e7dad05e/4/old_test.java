@Test
    public void testAddIdentifier() {
        String type = "cpe";
        String value = "cpe:/a:apache:struts:2.1.2";
        String url = "http://somewhere";
        Identifier expResult = new Identifier(type, value, url);

        Dependency instance = new Dependency();
        instance.addIdentifier(type, value, url);
        assertEquals(1, instance.getIdentifiers().size());
        assertTrue("Identifier doesn't contain expected result.", instance.getIdentifiers().contains(expResult));
    }