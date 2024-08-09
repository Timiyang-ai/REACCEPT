@Test
    public void testHexDigitMsb0ToBinary() {
        assertBinaryEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitMsb0ToBinary('0'));
        assertBinaryEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitMsb0ToBinary('1'));
        assertBinaryEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitMsb0ToBinary('2'));
        assertBinaryEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitMsb0ToBinary('3'));
        assertBinaryEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitMsb0ToBinary('4'));
        assertBinaryEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitMsb0ToBinary('5'));
        assertBinaryEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitMsb0ToBinary('6'));
        assertBinaryEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitMsb0ToBinary('7'));
        assertBinaryEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitMsb0ToBinary('8'));
        assertBinaryEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitMsb0ToBinary('9'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('A'));
        assertBinaryEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitMsb0ToBinary('a'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('B'));
        assertBinaryEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitMsb0ToBinary('b'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('C'));
        assertBinaryEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitMsb0ToBinary('c'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('D'));
        assertBinaryEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitMsb0ToBinary('d'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('E'));
        assertBinaryEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitMsb0ToBinary('e'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('F'));
        assertBinaryEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitMsb0ToBinary('f'));
        try {
            Conversion.hexDigitMsb0ToBinary('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }