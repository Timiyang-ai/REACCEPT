    public void test_random() {
        // There isn't a place for these tests so just stick them here
        assertEquals("Wrong value E",
                4613303445314885481L, Double.doubleToLongBits(Math.E));
        assertEquals("Wrong value PI",
                4614256656552045848L, Double.doubleToLongBits(Math.PI));

        for (int i = 500; i >= 0; i--) {
            double d = Math.random();
            assertTrue("Generated number is out of range: " + d, d >= 0.0
                    && d < 1.0);
        }
    }