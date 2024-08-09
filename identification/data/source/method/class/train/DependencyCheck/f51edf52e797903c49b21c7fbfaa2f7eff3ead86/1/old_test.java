@Test
    public void testCsvGav() {
        EscapeTool instance = new EscapeTool();
        Set<Identifier> ids = null;
        String expResult = "";
        String result = instance.csvGav(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        expResult = "";
        result = instance.csvGav(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "somegroup:something:1.0", ""));
        expResult = "";
        result = instance.csvGav(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        expResult = "somegroup:something:1.0";
        result = instance.csvGav(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        expResult = "somegroup:something:1.0";
        result = instance.csvGav(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("maven", "somegroup:something2:1.0", ""));
        expResult = "\"somegroup:something:1.0, somegroup:something2:1.0\"";
        String expResult2 = "\"somegroup:something2:1.0, somegroup:something:1.0\"";
        result = instance.csvGav(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }