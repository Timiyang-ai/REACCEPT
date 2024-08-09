@Test
    public void testLogGamma() {
        System.out.println("logGamma");
        assertTrue(Double.isInfinite(Gamma.logGamma(0)));

        assertEquals(0.0, Gamma.logGamma(1), 1E-7);
        assertEquals(0, Gamma.logGamma(2), 1E-7);
        assertEquals(Math.log(2.0), Gamma.logGamma(3), 1E-7);
        assertEquals(Math.log(6.0), Gamma.logGamma(4), 1E-7);

        assertEquals(-0.1207822, Gamma.logGamma(1.5), 1E-7);
        assertEquals(0.2846829, Gamma.logGamma(2.5), 1E-7);
        assertEquals(1.200974, Gamma.logGamma(3.5), 1E-6);
        assertEquals(2.453737, Gamma.logGamma(4.5), 1E-6);
    }