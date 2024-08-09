    @SuppressWarnings("boxing")
    public void test_nextAfter_DD() {
        // test for most cases without exception
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            final double start = NEXTAFTER_DD_START_CASES[i][0];
            final long nextUpBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][1]);
            final long nextDownBits = Double
                    .doubleToLongBits(NEXTAFTER_DD_START_CASES[i][2]);

            for (int j = 0; j < NEXTAFTER_DD_FD_DIRECTION_CASES.length; j++) {
                final double direction = NEXTAFTER_DD_FD_DIRECTION_CASES[j];
                final long resultBits = Double.doubleToLongBits(StrictMath
                        .nextAfter(start, direction));
                final long directionBits = Double.doubleToLongBits(direction);
                if (direction > start) {
                    assertEquals("Result should be next up-number.",
                            nextUpBits, resultBits);
                } else if (direction < start) {
                    assertEquals("Result should be next down-number.",
                            nextDownBits, resultBits);
                } else {
                    assertEquals("Result should be direction.", directionBits,
                            resultBits);
                }
            }
        }

        // test for cases with NaN
        for (int i = 0; i < NEXTAFTER_DD_START_CASES.length; i++) {
            assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                    .nextAfter(NEXTAFTER_DD_START_CASES[i][0], Double.NaN)));
        }
        for (int i = 0; i < NEXTAFTER_DD_FD_DIRECTION_CASES.length; i++) {
            assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                    .nextAfter(Double.NaN, NEXTAFTER_DD_FD_DIRECTION_CASES[i])));
        }
        assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                .nextAfter(Double.NaN, Double.NaN)));

        // test for exception
        try {
            StrictMath.nextAfter((Double) null, 2.3);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.nextAfter(2.3, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.nextAfter((Double) null, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }