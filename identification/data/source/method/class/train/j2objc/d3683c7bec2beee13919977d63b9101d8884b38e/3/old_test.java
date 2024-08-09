    @SuppressWarnings("boxing")
    public void test_nextUp_D() {
        if (System.getProperty("os.arch").equals("armv7")) {
          return;
        }
        // This method is semantically equivalent to nextAfter(d,
        // Double.POSITIVE_INFINITY),
        // so we use the data of test_nextAfter_DD
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            final double start = NEXTAFTER_DD_START_CASES[i][0];
            final long nextUpBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][1]);
            final long resultBits = Double.doubleToLongBits(StrictMath
                    .nextUp(start));
            assertEquals("Result should be next up-number.", nextUpBits,
                    resultBits);
        }

        // test for cases with NaN
        assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                .nextUp(Double.NaN)));

        // test for exception
        try {
            StrictMath.nextUp((Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }