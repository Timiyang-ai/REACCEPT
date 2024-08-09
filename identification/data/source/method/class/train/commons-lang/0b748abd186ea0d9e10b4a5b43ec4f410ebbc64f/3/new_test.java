@Test
    public void testHexDigitToBoolArray() {
        assertBoolArrayEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitToBoolArray('0'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitToBoolArray('1'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitToBoolArray('2'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitToBoolArray('3'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitToBoolArray('4'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitToBoolArray('5'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitToBoolArray('6'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitToBoolArray('7'));
        assertBoolArrayEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitToBoolArray('8'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitToBoolArray('9'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBoolArray('A'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBoolArray('a'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBoolArray('B'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBoolArray('b'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBoolArray('C'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBoolArray('c'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBoolArray('D'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBoolArray('d'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBoolArray('E'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBoolArray('e'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBoolArray('F'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBoolArray('f'));
        try {
            Conversion.hexDigitToBoolArray('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }