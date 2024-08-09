@Test
    public void testWhenComplete() {
        Assert.assertFalse(Task.supplyAsync(() -> {
            throw new IllegalStateException();
        }).whenComplete(exception -> {
            Assert.assertTrue(exception instanceof IllegalStateException);
        }).test());
    }