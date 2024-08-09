@Test
    public void testLogChoose() {
        System.out.println("logChoose");
        assertEquals(0.0, MathEx.logChoose(10, 0), 1E-6);
        assertEquals(2.302585, MathEx.logChoose(10, 1), 1E-6);
        assertEquals(3.806662, MathEx.logChoose(10, 2), 1E-6);
        assertEquals(4.787492, MathEx.logChoose(10, 3), 1E-6);
        assertEquals(5.347108, MathEx.logChoose(10, 4), 1E-6);
    }