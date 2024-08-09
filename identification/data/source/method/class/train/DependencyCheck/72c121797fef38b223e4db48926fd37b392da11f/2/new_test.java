@Test
    public void testCsvCpe() {
        EscapeTool instance = new EscapeTool();
        Set<Identifier> ids = null;
        String expResult = "\"\"";
        String result = instance.csvCpe(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        expResult = "\"\"";
        result = instance.csvCpe(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        expResult = "\"\"";
        result = instance.csvCpe(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        expResult = "cpe:/a:somegroup:something:1.0";
        result = instance.csvCpe(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        expResult = "cpe:/a:somegroup:something:1.0";
        result = instance.csvCpe(ids);
        assertEquals(expResult, result);
        
        ids = new HashSet<>();
        ids.add(new Identifier("cpe", "cpe:/a:somegroup:something:1.0", ""));
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        ids.add(new Identifier("cpe", "cpe:/a:somegroup2:something:1.2", ""));
        expResult = "\"cpe:/a:somegroup:something:1.0, cpe:/a:somegroup2:something:1.2\"";
        String expResult2 = "\"cpe:/a:somegroup2:something:1.2, cpe:/a:somegroup:something:1.0\"";
        result = instance.csvCpe(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }