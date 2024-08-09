    @Test
    public void closeTest() {
        Assertions.assertFalse(channel.isClosed());
        header.close();
        Assertions.assertTrue(channel.isClosed());
    }