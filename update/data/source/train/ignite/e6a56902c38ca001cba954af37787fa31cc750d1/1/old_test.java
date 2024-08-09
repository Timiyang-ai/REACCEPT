@Test
    public void testCancel2() throws Exception {
        testQueryCancel(grid(0), "pe", QRY_0, 50, TimeUnit.MILLISECONDS, false);
    }