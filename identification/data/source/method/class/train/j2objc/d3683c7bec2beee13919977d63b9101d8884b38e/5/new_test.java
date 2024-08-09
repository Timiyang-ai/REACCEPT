    @SuppressWarnings("boxing")
    public void test_getExponent_D() {
        for (int i = 0; i < GETEXPONENT_D_CASES.length; i++) {
            final double number = GETEXPONENT_D_CASES[i];
            final int result = GETEXPONENT_D_RESULTS[i];
            assertEquals("Wrong result of getExponent(double).", result,
                    StrictMath.getExponent(number));
        }

        try {
            StrictMath.getExponent((Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }