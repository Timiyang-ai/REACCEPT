@Test
    public void testLogFactorial() {
        System.out.println("logFactorial");
        assertEquals(0.0, MathEx.lfactorial(0), 1E-7);
        assertEquals(0.0, MathEx.lfactorial(1), 1E-7);
        assertEquals(Math.log(2.0), MathEx.lfactorial(2), 1E-7);
        assertEquals(Math.log(6.0), MathEx.lfactorial(3), 1E-7);
        assertEquals(Math.log(24.0), MathEx.lfactorial(4), 1E-7);
    }