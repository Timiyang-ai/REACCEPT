@Test
    public void testCsvIdentifiers() {
        EscapeTool instance = new EscapeTool();
        Set<Identifier> ids = null;
        String expResult = "";
        String result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        expResult = "";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        expResult = "";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("gav", "somegroup:something:1.0", ""));
        expResult = "somegroup:something:1.0";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("gav", "somegroup:something:1.0", ""));
        expResult = "somegroup:something:1.0";
        result = instance.csvIdentifiers(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("gav", "somegroup:something:1.0", ""));
        ids.add(new Identifier("gav", "somegroup2:something:1.2", ""));
        expResult = "\"somegroup:something:1.0, somegroup2:something:1.2\"";
        String expResult2 = "\"somegroup2:something:1.2, somegroup:something:1.0\"";
        result = instance.csvIdentifiers(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }