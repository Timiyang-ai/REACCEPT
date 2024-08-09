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
        ids.add(new Identifier("maven", "somegroup:something:1.0", ""));
        expResult = "\"\"";
        result = instance.csvCpeConfidence(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        Identifier i1 = new Identifier("cpe", "cpe:/a:somegroup:something:1.0", "");
        i1.setConfidence(Confidence.HIGH);
        ids.add(i1);
        expResult = "HIGH";
        result = instance.csvCpeConfidence(ids);
        assertEquals(expResult, result);

        ids = new HashSet<>();
        i1 = new Identifier("cpe", "cpe:/a:somegroup:something:1.0", "");
        i1.setConfidence(Confidence.HIGH);
        ids.add(i1);
        Identifier i2 = new Identifier("cpe", "cpe:/a:somegroup:something2:1.0", "");
        i2.setConfidence(Confidence.MEDIUM);
        ids.add(i2);
        Identifier i3 = new Identifier("maven", "somegroup:something:1.0", "");
        i3.setConfidence(Confidence.LOW);
        ids.add(i3);

        expResult = "\"HIGH, MEDIUM\"";
        String expResult2 = "\"MEDIUM, HIGH\"";
        result = instance.csvCpeConfidence(ids);
        assertTrue(expResult.equals(result) || expResult2.equals(result));
    }