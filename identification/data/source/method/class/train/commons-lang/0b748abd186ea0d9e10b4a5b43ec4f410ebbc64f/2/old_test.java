@Test
    public void testBoolsToHexDigit_2args() {
        boolean[] shortArray = new boolean[]{false, true, true};
        assertEquals('6', Conversion.boolsToHexDigit(shortArray, 0));
        assertEquals('3', Conversion.boolsToHexDigit(shortArray, 1));
        assertEquals('1', Conversion.boolsToHexDigit(shortArray, 2));
        boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        assertEquals('5', Conversion.boolsToHexDigit(longArray, 0));
        assertEquals('2', Conversion.boolsToHexDigit(longArray, 1));
        assertEquals('9', Conversion.boolsToHexDigit(longArray, 2));
        assertEquals('c', Conversion.boolsToHexDigit(longArray, 3));
        assertEquals('6', Conversion.boolsToHexDigit(longArray, 4));
        assertEquals('3', Conversion.boolsToHexDigit(longArray, 5));
        assertEquals('1', Conversion.boolsToHexDigit(longArray, 6));
    }