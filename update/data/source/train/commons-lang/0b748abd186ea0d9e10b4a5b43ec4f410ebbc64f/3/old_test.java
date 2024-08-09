@Test
    public void testHexDigitToBools() {
        assertBoolArrayEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitToBools('0'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitToBools('1'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitToBools('2'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitToBools('3'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitToBools('4'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitToBools('5'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitToBools('6'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitToBools('7'));
        assertBoolArrayEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitToBools('8'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitToBools('9'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBools('A'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBools('a'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBools('B'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBools('b'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBools('C'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBools('c'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBools('D'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBools('d'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBools('E'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBools('e'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBools('F'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBools('f'));
        try {
            Conversion.hexDigitToBools('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }