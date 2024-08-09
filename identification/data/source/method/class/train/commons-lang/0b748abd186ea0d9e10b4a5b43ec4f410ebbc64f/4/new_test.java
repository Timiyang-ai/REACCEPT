@Test
    public void testHexDigitMsb0ToInt() {
        assertEquals(0x0, Conversion.hexDigitMsb0ToInt('0'));
        assertEquals(0x8, Conversion.hexDigitMsb0ToInt('1'));
        assertEquals(0x4, Conversion.hexDigitMsb0ToInt('2'));
        assertEquals(0xC, Conversion.hexDigitMsb0ToInt('3'));
        assertEquals(0x2, Conversion.hexDigitMsb0ToInt('4'));
        assertEquals(0xA, Conversion.hexDigitMsb0ToInt('5'));
        assertEquals(0x6, Conversion.hexDigitMsb0ToInt('6'));
        assertEquals(0xE, Conversion.hexDigitMsb0ToInt('7'));
        assertEquals(0x1, Conversion.hexDigitMsb0ToInt('8'));
        assertEquals(0x9, Conversion.hexDigitMsb0ToInt('9'));
        assertEquals(0x5, Conversion.hexDigitMsb0ToInt('A'));
        assertEquals(0x5, Conversion.hexDigitMsb0ToInt('a'));
        assertEquals(0xD, Conversion.hexDigitMsb0ToInt('B'));
        assertEquals(0xD, Conversion.hexDigitMsb0ToInt('b'));
        assertEquals(0x3, Conversion.hexDigitMsb0ToInt('C'));
        assertEquals(0x3, Conversion.hexDigitMsb0ToInt('c'));
        assertEquals(0xB, Conversion.hexDigitMsb0ToInt('D'));
        assertEquals(0xB, Conversion.hexDigitMsb0ToInt('d'));
        assertEquals(0x7, Conversion.hexDigitMsb0ToInt('E'));
        assertEquals(0x7, Conversion.hexDigitMsb0ToInt('e'));
        assertEquals(0xF, Conversion.hexDigitMsb0ToInt('F'));
        assertEquals(0xF, Conversion.hexDigitMsb0ToInt('f'));
        try {
            Conversion.hexDigitMsb0ToInt('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }