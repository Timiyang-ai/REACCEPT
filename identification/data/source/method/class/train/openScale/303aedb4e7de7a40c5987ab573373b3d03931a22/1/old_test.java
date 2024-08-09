    @Test
    public void getBase10FloatTests() {
        double eps = 1e-9;  // margin of error for inexact floating point comparisons

        assertEquals(0.0f, trisaBodyAnalyze.getBase10Float(new byte[]{0, 0, 0, 0}, 0));
        assertEquals(0.0f, trisaBodyAnalyze.getBase10Float(new byte[]{0, 0, 0, -1}, 0));
        assertEquals(76.1f, trisaBodyAnalyze.getBase10Float(new byte[]{-70, 29, 0, -2}, 0), eps);
        assertEquals(1234.5678f, trisaBodyAnalyze.getBase10Float(new byte[]{78, 97, -68, -4}, 0), eps);
        assertEquals(12345678e20f, trisaBodyAnalyze.getBase10Float(new byte[]{78, 97, -68, 20}, 0));
        assertEquals(12345678e-20f, trisaBodyAnalyze.getBase10Float(new byte[]{78, 97, -68, -20}, 0), eps);

        byte[] data = new byte[]{1,2,3,4,5};
        assertEquals(0x030201*1e4f, trisaBodyAnalyze.getBase10Float(data, 0));
        assertEquals(0x040302*1e5f, trisaBodyAnalyze.getBase10Float(data, 1));

        assertThrows(IndexOutOfBoundsException.class, getBase10FloatRunnable(data, -1));
        assertThrows(IndexOutOfBoundsException.class, getBase10FloatRunnable(data, 5));
        assertThrows(IndexOutOfBoundsException.class, getBase10FloatRunnable(new byte[]{1,2,3}, 0));
    }