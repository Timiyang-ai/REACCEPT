@Test
    public void testCpeMatches() {
        Identifier identifier = new Identifier("cwe", "cpe:/a:microsoft:.net_framework:4.5", "some url not needed for this test");

        PropertyType cpe = new PropertyType();
        cpe.setValue("cpe:/a:microsoft:.net_framework:4.5");

        SuppressionRule instance = new SuppressionRule();
        boolean expResult = true;
        boolean result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("cpe:/a:microsoft:.net_framework:4.0");
        expResult = false;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("CPE:/a:microsoft:.net_framework:4.5");
        cpe.setCaseSensitive(true);
        expResult = false;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("cpe:/a:microsoft:.net_framework");
        cpe.setCaseSensitive(false);
        expResult = true;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("cpe:/a:microsoft:.*");
        cpe.setRegex(true);
        expResult = true;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("CPE:/a:microsoft:.*");
        cpe.setRegex(true);
        cpe.setCaseSensitive(true);
        expResult = false;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);

        cpe.setValue("cpe:/a:apache:.*");
        cpe.setRegex(true);
        cpe.setCaseSensitive(false);
        expResult = false;
        result = instance.identifierMatches(cpe, identifier);
        assertEquals(expResult, result);
    }