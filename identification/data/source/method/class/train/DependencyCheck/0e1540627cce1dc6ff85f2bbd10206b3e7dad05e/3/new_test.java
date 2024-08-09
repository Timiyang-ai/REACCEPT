@Test
    public void testCsvIdentifiers() {
        EscapeTool instance = new EscapeTool();
        Set<Identifier> ids = null;
        String expResult = "\"\"";
        String result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        expResult = "\"\"";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new GenericIdentifier("somegroup:something:1.0", Confidence.HIGH));
        expResult = "somegroup:something:1.0";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new GenericIdentifier("somegroup:something:1.0", Confidence.HIGH));
        ids.add(new GenericIdentifier("somegroup2:something:1.2", Confidence.HIGH));
        expResult = "\"somegroup:something:1.0, somegroup2:something:1.2\"";
        String expResult2 = "\"somegroup2:something:1.2, somegroup:something:1.0\"";
        result = instance.csvIdentifiers(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }