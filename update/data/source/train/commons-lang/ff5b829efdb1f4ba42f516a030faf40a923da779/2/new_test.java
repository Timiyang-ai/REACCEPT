@Test
    public void testBinaryToHexDigit() {
        assertEquals(
            '0', Conversion.binaryToHexDigit(new boolean[]{false, false, false, false}));
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true, false, false, false}));
        assertEquals('2', Conversion.binaryToHexDigit(new boolean[]{false, true, false, false}));
        assertEquals('3', Conversion.binaryToHexDigit(new boolean[]{true, true, false, false}));
        assertEquals('4', Conversion.binaryToHexDigit(new boolean[]{false, false, true, false}));
        assertEquals('5', Conversion.binaryToHexDigit(new boolean[]{true, false, true, false}));
        assertEquals('6', Conversion.binaryToHexDigit(new boolean[]{false, true, true, false}));
        assertEquals('7', Conversion.binaryToHexDigit(new boolean[]{true, true, true, false}));
        assertEquals('8', Conversion.binaryToHexDigit(new boolean[]{false, false, false, true}));
        assertEquals('9', Conversion.binaryToHexDigit(new boolean[]{true, false, false, true}));
        assertEquals('a', Conversion.binaryToHexDigit(new boolean[]{false, true, false, true}));
        assertEquals('b', Conversion.binaryToHexDigit(new boolean[]{true, true, false, true}));
        assertEquals('c', Conversion.binaryToHexDigit(new boolean[]{false, false, true, true}));
        assertEquals('d', Conversion.binaryToHexDigit(new boolean[]{true, false, true, true}));
        assertEquals('e', Conversion.binaryToHexDigit(new boolean[]{false, true, true, true}));
        assertEquals('f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true}));
        assertEquals('1', Conversion.binaryToHexDigit(new boolean[]{true}));
        assertEquals(
            'f', Conversion.binaryToHexDigit(new boolean[]{true, true, true, true, true}));
        try {
            Conversion.binaryToHexDigit(new boolean[]{});
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }