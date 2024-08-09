@Test
    public void testHexDigitMsb0ToBoolArray() {
        assertBoolArrayEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitMsb0ToBoolArray('0'));
        assertBoolArrayEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitMsb0ToBoolArray('1'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitMsb0ToBoolArray('2'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitMsb0ToBoolArray('3'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitMsb0ToBoolArray('4'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitMsb0ToBoolArray('5'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitMsb0ToBoolArray('6'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitMsb0ToBoolArray('7'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitMsb0ToBoolArray('8'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitMsb0ToBoolArray('9'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBoolArray('A'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBoolArray('a'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBoolArray('B'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBoolArray('b'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBoolArray('C'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBoolArray('c'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBoolArray('D'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBoolArray('d'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBoolArray('E'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBoolArray('e'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBoolArray('F'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBoolArray('f'));
        try {
            Conversion.hexDigitMsb0ToBoolArray('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }