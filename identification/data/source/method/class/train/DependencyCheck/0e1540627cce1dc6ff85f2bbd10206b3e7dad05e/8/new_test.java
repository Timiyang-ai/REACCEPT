@Test
    public void testCsvCpeConfidence() {
        EscapeTool instance = new EscapeTool();
        Set<Identifier> ids = null;
        String expResult = "\"\"";
        String result = instance.csvCpeConfidence(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        expResult = "\"\"";
        result = instance.csvCpeConfidence(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        GenericIdentifier i1 = new GenericIdentifier("cpe:/a:somegroup:something:1.0", Confidence.HIGH);
        ids.add(i1);
        expResult = "HIGH";
        result = instance.csvCpeConfidence(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        i1 = new GenericIdentifier("cpe:/a:somegroup:something:1.0", Confidence.HIGH);
        ids.add(i1);
        GenericIdentifier i2 = new GenericIdentifier("cpe:/a:somegroup:something2:1.0", Confidence.MEDIUM);
        ids.add(i2);

        expResult = "\"HIGH, MEDIUM\"";
        String expResult2 = "\"MEDIUM, HIGH\"";
        result = instance.csvCpeConfidence(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }