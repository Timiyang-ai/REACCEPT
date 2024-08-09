    @SuppressWarnings("boxing")
    public void test_log10_D() {
        // Test for special cases
        assertTrue(Double.isNaN(Math.log10(Double.NaN)));
        assertTrue(Double.isNaN(Math.log10(-2541.05745687234187532)));
        assertTrue(Double.isNaN(Math.log10(-0.1)));
        assertEquals(Double.POSITIVE_INFINITY, Math.log10(Double.POSITIVE_INFINITY));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(0.0));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(+0.0));
        assertEquals(Double.NEGATIVE_INFINITY, Math.log10(-0.0));

        assertEquals(3.0, Math.log10(1000.0));
        assertEquals(14.0, Math.log10(Math.pow(10, 14)));
        assertEquals(3.7389561269540406, Math.log10(5482.2158));
        assertEquals(14.661551142893833, Math.log10(458723662312872.125782332587));
        assertEquals(-0.9083828622192334, Math.log10(0.12348583358871));
        assertEquals(308.25471555991675, Math.log10(Double.MAX_VALUE));
        assertEquals(-323.3062153431158, Math.log10(Double.MIN_VALUE));
    }