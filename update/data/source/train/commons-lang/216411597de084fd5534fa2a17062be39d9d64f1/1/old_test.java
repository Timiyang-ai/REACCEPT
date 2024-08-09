@Test
    public void testBoolsToHexDigit() {
        assertEquals('0', Conversion.boolsToHexDigit(new boolean[]{false, false, false, false}));
        assertEquals('1', Conversion.boolsToHexDigit(new boolean[]{true, false, false, false}));
        assertEquals('2', Conversion.boolsToHexDigit(new boolean[]{false, true, false, false}));
        assertEquals('3', Conversion.boolsToHexDigit(new boolean[]{true, true, false, false}));
        assertEquals('4', Conversion.boolsToHexDigit(new boolean[]{false, false, true, false}));
        assertEquals('5', Conversion.boolsToHexDigit(new boolean[]{true, false, true, false}));
        assertEquals('6', Conversion.boolsToHexDigit(new boolean[]{false, true, true, false}));
        assertEquals('7', Conversion.boolsToHexDigit(new boolean[]{true, true, true, false}));
        assertEquals('8', Conversion.boolsToHexDigit(new boolean[]{false, false, false, true}));
        assertEquals('9', Conversion.boolsToHexDigit(new boolean[]{true, false, false, true}));
        assertEquals('A', Conversion.boolsToHexDigit(new boolean[]{false, true, false, true}));
        assertEquals('B', Conversion.boolsToHexDigit(new boolean[]{true, true, false, true}));
        assertEquals('C', Conversion.boolsToHexDigit(new boolean[]{false, false, true, true}));
        assertEquals('D', Conversion.boolsToHexDigit(new boolean[]{true, false, true, true}));
        assertEquals('E', Conversion.boolsToHexDigit(new boolean[]{false, true, true, true}));
        assertEquals('F', Conversion.boolsToHexDigit(new boolean[]{true, true, true, true}));
    }