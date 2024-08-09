    @SuppressWarnings("boxing")
    public void test_copySign_DD() {
        for (int i = 0; i < COPYSIGN_DD_CASES.length; i++) {
            final double magnitude = COPYSIGN_DD_CASES[i];
            final long absMagnitudeBits = Double.doubleToLongBits(StrictMath
                    .abs(magnitude));
            final long negMagnitudeBits = Double.doubleToLongBits(-StrictMath
                    .abs(magnitude));

            // cases for NaN
            assertEquals("If the sign is NaN, the result should be positive.",
                    absMagnitudeBits, Double.doubleToLongBits(StrictMath
                    .copySign(magnitude, Double.NaN)));
            assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                    .copySign(Double.NaN, magnitude)));

            for (int j = 0; j < COPYSIGN_DD_CASES.length; j++) {
                final double sign = COPYSIGN_DD_CASES[j];
                final long resultBits = Double.doubleToLongBits(StrictMath
                        .copySign(magnitude, sign));

                if (sign > 0 || Double.valueOf(+0.0).equals(sign)
                        || Double.valueOf(0.0).equals(sign)) {
                    assertEquals(
                            "If the sign is positive, the result should be positive.",
                            absMagnitudeBits, resultBits);
                }
                if (sign < 0 || Double.valueOf(-0.0).equals(sign)) {
                    assertEquals(
                            "If the sign is negative, the result should be negative.",
                            negMagnitudeBits, resultBits);
                }
            }
        }

        assertTrue("The result should be NaN.", Double.isNaN(StrictMath
                .copySign(Double.NaN, Double.NaN)));

        try {
            StrictMath.copySign((Double) null, 2.3);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.copySign(2.3, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.copySign((Double) null, (Double) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }

        double d = Double.longBitsToDouble(0xfff8000000000000L);
        assertEquals(1.0, StrictMath.copySign(1.0, d), 0d);
    }