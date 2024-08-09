@Test
    public void testHexDigitToBinary() {
        assertBinaryEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitToBinary('0'));
        assertBinaryEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitToBinary('1'));
        assertBinaryEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitToBinary('2'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitToBinary('3'));
        assertBinaryEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitToBinary('4'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitToBinary('5'));
        assertBinaryEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitToBinary('6'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitToBinary('7'));
        assertBinaryEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitToBinary('8'));
        assertBinaryEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitToBinary('9'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('A'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitToBinary('a'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('B'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitToBinary('b'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('C'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitToBinary('c'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('D'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitToBinary('d'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('E'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitToBinary('e'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('F'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitToBinary('f'));
        try {
            Conversion.hexDigitToBinary('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }