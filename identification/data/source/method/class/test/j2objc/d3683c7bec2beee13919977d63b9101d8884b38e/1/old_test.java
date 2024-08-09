    public void test_signum_D() {
        assertTrue(Double.isNaN(StrictMath.signum(Double.NaN)));
        assertEquals(Double.doubleToLongBits(0.0), Double
                .doubleToLongBits(StrictMath.signum(0.0)));
        assertEquals(Double.doubleToLongBits(+0.0), Double
                .doubleToLongBits(StrictMath.signum(+0.0)));
        assertEquals(Double.doubleToLongBits(-0.0), Double
                .doubleToLongBits(StrictMath.signum(-0.0)));

        assertEquals(1.0, StrictMath.signum(253681.2187962), 0D);
        assertEquals(-1.0, StrictMath.signum(-125874693.56), 0D);
        if (!System.getProperty("os.arch").equals("armv7")) {
          assertEquals(1.0, StrictMath.signum(1.2587E-308), 0D);
          assertEquals(-1.0, StrictMath.signum(-1.2587E-308), 0D);
        }

        assertEquals(1.0, StrictMath.signum(Double.MAX_VALUE), 0D);
        if (!System.getProperty("os.arch").equals("armv7")) {
          assertEquals(1.0, StrictMath.signum(Double.MIN_VALUE), 0D);
        }
        assertEquals(-1.0, StrictMath.signum(-Double.MAX_VALUE), 0D);
        if (!System.getProperty("os.arch").equals("armv7")) {
          assertEquals(-1.0, StrictMath.signum(-Double.MIN_VALUE), 0D);
        }
        assertEquals(1.0, StrictMath.signum(Double.POSITIVE_INFINITY), 0D);
        assertEquals(-1.0, StrictMath.signum(Double.NEGATIVE_INFINITY), 0D);

    }