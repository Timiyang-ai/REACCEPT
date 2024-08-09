@Test
    public void testHexDigitM0ToInt() {
        assertEquals(0x0, Conversion.hexDigitM0ToInt('0'));
        assertEquals(0x8, Conversion.hexDigitM0ToInt('1'));
        assertEquals(0x4, Conversion.hexDigitM0ToInt('2'));
        assertEquals(0xC, Conversion.hexDigitM0ToInt('3'));
        assertEquals(0x2, Conversion.hexDigitM0ToInt('4'));
        assertEquals(0xA, Conversion.hexDigitM0ToInt('5'));
        assertEquals(0x6, Conversion.hexDigitM0ToInt('6'));
        assertEquals(0xE, Conversion.hexDigitM0ToInt('7'));
        assertEquals(0x1, Conversion.hexDigitM0ToInt('8'));
        assertEquals(0x9, Conversion.hexDigitM0ToInt('9'));
        assertEquals(0x5, Conversion.hexDigitM0ToInt('A'));
        assertEquals(0x5, Conversion.hexDigitM0ToInt('a'));
        assertEquals(0xD, Conversion.hexDigitM0ToInt('B'));
        assertEquals(0xD, Conversion.hexDigitM0ToInt('b'));
        assertEquals(0x3, Conversion.hexDigitM0ToInt('C'));
        assertEquals(0x3, Conversion.hexDigitM0ToInt('c'));
        assertEquals(0xB, Conversion.hexDigitM0ToInt('D'));
        assertEquals(0xB, Conversion.hexDigitM0ToInt('d'));
        assertEquals(0x7, Conversion.hexDigitM0ToInt('E'));
        assertEquals(0x7, Conversion.hexDigitM0ToInt('e'));
        assertEquals(0xF, Conversion.hexDigitM0ToInt('F'));
        assertEquals(0xF, Conversion.hexDigitM0ToInt('f'));
        try {
            Conversion.hexDigitM0ToInt('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }