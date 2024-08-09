@Test
    public void testBitcoinValueToPlainString() {
        // null argument check
        try {
            bitcoinValueToPlainString(null);
            org.junit.Assert.fail("Expecting IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Value cannot be null"));
        }

        assertEquals("0.0015", bitcoinValueToPlainString(Coin.valueOf(150000)));
        assertEquals("1.23", bitcoinValueToPlainString(toNanoCoins("1.23")));

        assertEquals("0.1", bitcoinValueToPlainString(toNanoCoins("0.1")));
        assertEquals("1.1", bitcoinValueToPlainString(toNanoCoins("1.1")));
        assertEquals("21.12", bitcoinValueToPlainString(toNanoCoins("21.12")));
        assertEquals("321.123", bitcoinValueToPlainString(toNanoCoins("321.123")));
        assertEquals("4321.1234", bitcoinValueToPlainString(toNanoCoins("4321.1234")));
        assertEquals("54321.12345", bitcoinValueToPlainString(toNanoCoins("54321.12345")));
        assertEquals("654321.123456", bitcoinValueToPlainString(toNanoCoins("654321.123456")));
        assertEquals("7654321.1234567", bitcoinValueToPlainString(toNanoCoins("7654321.1234567")));
        try {
            assertEquals("87654321.12345678", bitcoinValueToPlainString(toNanoCoins("87654321.12345678")));
            Assert.fail();  // More than MAX_MONEY
        } catch (Exception e) {}

        // check there are no trailing zeros
        assertEquals("1", bitcoinValueToPlainString(toNanoCoins("1.0")));
        assertEquals("2", bitcoinValueToPlainString(toNanoCoins("2.00")));
        assertEquals("3", bitcoinValueToPlainString(toNanoCoins("3.000")));
        assertEquals("4", bitcoinValueToPlainString(toNanoCoins("4.0000")));
        assertEquals("5", bitcoinValueToPlainString(toNanoCoins("5.00000")));
        assertEquals("6", bitcoinValueToPlainString(toNanoCoins("6.000000")));
        assertEquals("7", bitcoinValueToPlainString(toNanoCoins("7.0000000")));
        assertEquals("8", bitcoinValueToPlainString(toNanoCoins("8.00000000")));
    }