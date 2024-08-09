@Test
    public void testCsv() {
        String text = null;
        EscapeTool instance = new EscapeTool();
        String expResult = null;
        String result = instance.csv(text);
        assertEquals(expResult, result);
        
        text = "";
        expResult = "";
        result = instance.csv(text);
        assertEquals(expResult, result);
        
        text = "one, two";
        expResult = "\"one, two\"";
        result = instance.csv(text);
        assertEquals(expResult, result);
    }