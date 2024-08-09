@Test
    public void testBoolArrayToHexDigit() {
        assertEquals(
            '0', Conversion.boolArrayToHexDigit(new boolean[]{false, false, false, false}));
        assertEquals(
            '1', Conversion.boolArrayToHexDigit(new boolean[]{true, false, false, false}));
        assertEquals(
            '2', Conversion.boolArrayToHexDigit(new boolean[]{false, true, false, false}));
        assertEquals(
            '3', Conversion.boolArrayToHexDigit(new boolean[]{true, true, false, false}));
        assertEquals(
            '4', Conversion.boolArrayToHexDigit(new boolean[]{false, false, true, false}));
        assertEquals(
            '5', Conversion.boolArrayToHexDigit(new boolean[]{true, false, true, false}));
        assertEquals(
            '6', Conversion.boolArrayToHexDigit(new boolean[]{false, true, true, false}));
        assertEquals(
            '7', Conversion.boolArrayToHexDigit(new boolean[]{true, true, true, false}));
        assertEquals(
            '8', Conversion.boolArrayToHexDigit(new boolean[]{false, false, false, true}));
        assertEquals(
            '9', Conversion.boolArrayToHexDigit(new boolean[]{true, false, false, true}));
        assertEquals(
            'a', Conversion.boolArrayToHexDigit(new boolean[]{false, true, false, true}));
        assertEquals(
            'b', Conversion.boolArrayToHexDigit(new boolean[]{true, true, false, true}));
        assertEquals(
            'c', Conversion.boolArrayToHexDigit(new boolean[]{false, false, true, true}));
        assertEquals(
            'd', Conversion.boolArrayToHexDigit(new boolean[]{true, false, true, true}));
        assertEquals(
            'e', Conversion.boolArrayToHexDigit(new boolean[]{false, true, true, true}));
        assertEquals('f', Conversion.boolArrayToHexDigit(new boolean[]{true, true, true, true}));
        assertEquals('1', Conversion.boolArrayToHexDigit(new boolean[]{true}));
        assertEquals(
            'f', Conversion.boolArrayToHexDigit(new boolean[]{true, true, true, true, true}));
        try {
            Conversion.boolArrayToHexDigit(new boolean[]{});
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }