    @SuppressWarnings("boxing")
    public void test_scalb_DI() {
        if (System.getProperty("os.arch").equals("armv7")) {
          return;
        }
        // result is normal
        assertEquals(4.1422946304E7, StrictMath.scalb(1.2345, 25));
        assertEquals(3.679096698760986E-8, StrictMath.scalb(1.2345, -25));
        assertEquals(1.2345, StrictMath.scalb(1.2345, 0));
        assertEquals(7868514.304, StrictMath.scalb(0.2345, 25));

        double normal = StrictMath.scalb(0.2345, -25);
        assertEquals(6.98864459991455E-9, normal);
        // precision kept
        assertEquals(0.2345, StrictMath.scalb(normal, 25));

        assertEquals(0.2345, StrictMath.scalb(0.2345, 0));
        assertEquals(-4.1422946304E7, StrictMath.scalb(-1.2345, 25));
        assertEquals(-6.98864459991455E-9, StrictMath.scalb(-0.2345, -25));
        assertEquals(2.0, StrictMath.scalb(Double.MIN_NORMAL / 2, 1024));
        assertEquals(64.0, StrictMath.scalb(Double.MIN_VALUE, 1080));
        assertEquals(234, StrictMath.getExponent(StrictMath.scalb(1.0, 234)));
        assertEquals(3.9999999999999996, StrictMath.scalb(Double.MAX_VALUE,
                Double.MIN_EXPONENT));

        // result is near infinity
        double halfMax = StrictMath.scalb(1.0, Double.MAX_EXPONENT);
        assertEquals(8.98846567431158E307, halfMax);
        assertEquals(Double.MAX_VALUE, halfMax - StrictMath.ulp(halfMax)
                + halfMax);
        assertEquals(Double.POSITIVE_INFINITY, halfMax + halfMax);
        assertEquals(1.7976931348623155E308, StrictMath.scalb(1.0 - StrictMath
                .ulp(1.0), Double.MAX_EXPONENT + 1));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                1.0 - StrictMath.ulp(1.0), Double.MAX_EXPONENT + 2));

        halfMax = StrictMath.scalb(-1.0, Double.MAX_EXPONENT);
        assertEquals(-8.98846567431158E307, halfMax);
        assertEquals(-Double.MAX_VALUE, halfMax + StrictMath.ulp(halfMax)
                + halfMax);
        assertEquals(Double.NEGATIVE_INFINITY, halfMax + halfMax);

        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(0.345, 1234));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath
                .scalb(44.345E102, 934));
        assertEquals(Double.NEGATIVE_INFINITY, StrictMath.scalb(-44.345E102,
                934));

        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                Double.MIN_NORMAL / 2, 4000));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                Double.MIN_VALUE, 8000));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                Double.MAX_VALUE, 1));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                Double.POSITIVE_INFINITY, 0));
        assertEquals(Double.POSITIVE_INFINITY, StrictMath.scalb(
                Double.POSITIVE_INFINITY, -1));
        assertEquals(Double.NEGATIVE_INFINITY, StrictMath.scalb(
                Double.NEGATIVE_INFINITY, -1));
        assertEquals(Double.NEGATIVE_INFINITY, StrictMath.scalb(
                Double.NEGATIVE_INFINITY, Double.MIN_EXPONENT));

        // result is subnormal/zero
        long posZeroBits = Double.doubleToLongBits(+0.0);
        long negZeroBits = Double.doubleToLongBits(-0.0);
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                +0.0, Integer.MAX_VALUE)));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                +0.0, -123)));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                +0.0, 0)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -0.0, 123)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -0.0, Integer.MIN_VALUE)));

        assertEquals(Double.MIN_VALUE, StrictMath.scalb(1.0, -1074));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(1.0,
                -1075)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -1.0, -1075)));

        // precision lost
        assertEquals(StrictMath.scalb(21.405, -1078), StrictMath.scalb(21.405,
                -1079));
        assertEquals(Double.MIN_VALUE, StrictMath.scalb(21.405, -1079));
        assertEquals(-Double.MIN_VALUE, StrictMath.scalb(-21.405, -1079));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                21.405, -1080)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -21.405, -1080)));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                Double.MIN_VALUE, -1)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -Double.MIN_VALUE, -1)));
        assertEquals(Double.MIN_VALUE, StrictMath.scalb(Double.MIN_NORMAL, -52));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                Double.MIN_NORMAL, -53)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -Double.MIN_NORMAL, -53)));
        assertEquals(Double.MIN_VALUE, StrictMath
                .scalb(Double.MAX_VALUE, -2098));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                Double.MAX_VALUE, -2099)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -Double.MAX_VALUE, -2099)));
        assertEquals(Double.MIN_VALUE, StrictMath.scalb(Double.MIN_NORMAL / 3,
                -51));
        assertEquals(posZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                Double.MIN_NORMAL / 3, -52)));
        assertEquals(negZeroBits, Double.doubleToLongBits(StrictMath.scalb(
                -Double.MIN_NORMAL / 3, -52)));
        double subnormal = StrictMath.scalb(Double.MIN_NORMAL / 3, -25);
        assertEquals(2.2104123E-316, subnormal);
        // precision lost
        assertFalse(Double.MIN_NORMAL / 3 == StrictMath.scalb(subnormal, 25));

        // NaN
        assertTrue(Double.isNaN(StrictMath.scalb(Double.NaN, 1)));
        assertTrue(Double.isNaN(StrictMath.scalb(Double.NaN, 0)));
        assertTrue(Double.isNaN(StrictMath.scalb(Double.NaN, -120)));

        assertEquals(1283457024, Double.doubleToLongBits(StrictMath.scalb(
                Double.MIN_VALUE * 153, 23)));
        assertEquals(-9223372035571318784L, Double.doubleToLongBits(StrictMath
                .scalb(-Double.MIN_VALUE * 153, 23)));
        assertEquals(36908406321184768L, Double.doubleToLongBits(StrictMath
                .scalb(Double.MIN_VALUE * 153, 52)));
        assertEquals(-9186463630533591040L, Double.doubleToLongBits(StrictMath
                .scalb(-Double.MIN_VALUE * 153, 52)));

        // test for exception
        try {
            StrictMath.scalb((Double) null, (Integer) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.scalb(1.0, (Integer) null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
        try {
            StrictMath.scalb((Double) null, 1);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }