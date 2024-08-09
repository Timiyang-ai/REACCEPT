@Test
    public void testCancel2() throws Exception {
        testQueryCancel(grid(0), "pe", QRY_LONG, 50, TimeUnit.MILLISECONDS, false, true);
    }