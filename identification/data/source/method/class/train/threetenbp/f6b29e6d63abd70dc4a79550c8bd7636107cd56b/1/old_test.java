@Test(groups={"tck"})
    public void now() {
        Instant expected = Instant.now(TimeSource.system());
        Instant test = Instant.now();
        BigInteger diff = test.toEpochNano().subtract(expected.toEpochNano()).abs();
        if (diff.compareTo(BigInteger.valueOf(100000000)) >= 0) {
            // may be date change
            expected = Instant.now(TimeSource.system());
            test = Instant.now();
            diff = test.toEpochNano().subtract(expected.toEpochNano()).abs();
        }
        assertTrue(diff.compareTo(BigInteger.valueOf(100000000)) < 0);  // less than 0.1 secs
    }