    @SuppressWarnings("boxing")
    public void test_ulp_D() {
        // Test for special cases
        assertTrue("Should return NaN", Double
                .isNaN(StrictMath.ulp(Double.NaN)));
        assertEquals("Returned incorrect value", Double.POSITIVE_INFINITY,
                StrictMath.ulp(Double.POSITIVE_INFINITY), 0D);
        assertEquals("Returned incorrect value", Double.POSITIVE_INFINITY,
                StrictMath.ulp(Double.NEGATIVE_INFINITY), 0D);
        assertEquals("Returned incorrect value", Double.MIN_VALUE, StrictMath
                .ulp(0.0), 0D);
        assertEquals("Returned incorrect value", Double.MIN_VALUE, StrictMath
                .ulp(+0.0), 0D);
        assertEquals("Returned incorrect value", Double.MIN_VALUE, StrictMath
                .ulp(-0.0), 0D);
        assertEquals("Returned incorrect value", StrictMath.pow(2, 971),
                StrictMath.ulp(Double.MAX_VALUE), 0D);
        assertEquals("Returned incorrect value", StrictMath.pow(2, 971),
                StrictMath.ulp(-Double.MAX_VALUE), 0D);

        assertEquals("Returned incorrect value", Double.MIN_VALUE, StrictMath
                .ulp(Double.MIN_VALUE), 0D);
        assertEquals("Returned incorrect value", Double.MIN_VALUE, StrictMath
                .ulp(-Double.MIN_VALUE), 0D);

        assertEquals("Returned incorrect value", 2.220446049250313E-16,
                StrictMath.ulp(1.0), 0D);
        assertEquals("Returned incorrect value", 2.220446049250313E-16,
                StrictMath.ulp(-1.0), 0D);
        assertEquals("Returned incorrect value", 2.2737367544323206E-13,
                StrictMath.ulp(1153.0), 0D);
    }