    public void test_cbrt_D() {
        //Test for special situations
        assertTrue(Double.isNaN(Math.cbrt(Double.NaN)));
        assertEquals(Double.POSITIVE_INFINITY, Math.cbrt(Double.POSITIVE_INFINITY), 0D);
        assertEquals(Double.NEGATIVE_INFINITY, Math.cbrt(Double.NEGATIVE_INFINITY), 0D);
        assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(Math.cbrt(0.0)));
        assertEquals(Double.doubleToLongBits(+0.0), Double.doubleToLongBits(Math.cbrt(+0.0)));
        assertEquals(Double.doubleToLongBits(-0.0), Double.doubleToLongBits(Math.cbrt(-0.0)));

        assertEquals(3.0, Math.cbrt(27.0), 0D);
        assertEquals(23.111993172558684, Math.cbrt(12345.6), Math.ulp(23.111993172558684));
        assertEquals(5.643803094122362E102, Math.cbrt(Double.MAX_VALUE), 0D);
        assertEquals(0.01, Math.cbrt(0.000001), 0D);

        assertEquals(-3.0, Math.cbrt(-27.0), 0D);
        assertEquals(-23.111993172558684, Math.cbrt(-12345.6), Math.ulp(-23.111993172558684));
        assertEquals(1.7031839360032603E-108, Math.cbrt(Double.MIN_VALUE), 0D);
        assertEquals(-0.01, Math.cbrt(-0.000001), 0D);
    }