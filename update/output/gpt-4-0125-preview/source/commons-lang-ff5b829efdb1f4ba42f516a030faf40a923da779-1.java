@Test
    public void testHexDigitToBinary() {
        assertBoolArrayEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitToBinary('0'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitToBinary('1'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitToBinary('2'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitToBinary('3'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitToBinary('4'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitToBinary('5'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitToBinary('6'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitToBinary('7'));
        assertBoolArrayEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitToBinary('8'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitToBinary('9'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('A'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('a'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('B'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('b'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('C'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('c'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('D'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('d'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('E'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('e'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('F'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('f'));
        try {
            Conversion.hexDigitToBinary('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }