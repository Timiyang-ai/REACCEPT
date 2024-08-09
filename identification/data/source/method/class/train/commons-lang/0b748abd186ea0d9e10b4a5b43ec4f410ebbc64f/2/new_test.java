@Test
    public void testBoolArrayToHexDigit_2args() {
        boolean[] shortArray = new boolean[]{false, true, true};
        assertEquals('6', Conversion.boolArrayToHexDigit(shortArray, 0));
        assertEquals('3', Conversion.boolArrayToHexDigit(shortArray, 1));
        assertEquals('1', Conversion.boolArrayToHexDigit(shortArray, 2));
        boolean[] longArray = new boolean[]{true, false, true, false, false, true, true};
        assertEquals('5', Conversion.boolArrayToHexDigit(longArray, 0));
        assertEquals('2', Conversion.boolArrayToHexDigit(longArray, 1));
        assertEquals('9', Conversion.boolArrayToHexDigit(longArray, 2));
        assertEquals('c', Conversion.boolArrayToHexDigit(longArray, 3));
        assertEquals('6', Conversion.boolArrayToHexDigit(longArray, 4));
        assertEquals('3', Conversion.boolArrayToHexDigit(longArray, 5));
        assertEquals('1', Conversion.boolArrayToHexDigit(longArray, 6));
    }