@Test
    public void testHexDigitM0ToBools() {
        assertBoolArrayEquals(
            new boolean[]{false, false, false, false}, Conversion.hexDigitM0ToBools('0'));
        assertBoolArrayEquals(
            new boolean[]{false, false, false, true}, Conversion.hexDigitM0ToBools('1'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, false}, Conversion.hexDigitM0ToBools('2'));
        assertBoolArrayEquals(
            new boolean[]{false, false, true, true}, Conversion.hexDigitM0ToBools('3'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, false}, Conversion.hexDigitM0ToBools('4'));
        assertBoolArrayEquals(
            new boolean[]{false, true, false, true}, Conversion.hexDigitM0ToBools('5'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, false}, Conversion.hexDigitM0ToBools('6'));
        assertBoolArrayEquals(
            new boolean[]{false, true, true, true}, Conversion.hexDigitM0ToBools('7'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, false}, Conversion.hexDigitM0ToBools('8'));
        assertBoolArrayEquals(
            new boolean[]{true, false, false, true}, Conversion.hexDigitM0ToBools('9'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitM0ToBools('A'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, false}, Conversion.hexDigitM0ToBools('a'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitM0ToBools('B'));
        assertBoolArrayEquals(
            new boolean[]{true, false, true, true}, Conversion.hexDigitM0ToBools('b'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitM0ToBools('C'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, false}, Conversion.hexDigitM0ToBools('c'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitM0ToBools('D'));
        assertBoolArrayEquals(
            new boolean[]{true, true, false, true}, Conversion.hexDigitM0ToBools('d'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitM0ToBools('E'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, false}, Conversion.hexDigitM0ToBools('e'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitM0ToBools('F'));
        assertBoolArrayEquals(
            new boolean[]{true, true, true, true}, Conversion.hexDigitM0ToBools('f'));
        try {
            Conversion.hexDigitM0ToBools('G');
            fail("Thrown " + IllegalArgumentException.class.getName() + " expected");
        } catch (final IllegalArgumentException e) {
            // OK
        }
    }