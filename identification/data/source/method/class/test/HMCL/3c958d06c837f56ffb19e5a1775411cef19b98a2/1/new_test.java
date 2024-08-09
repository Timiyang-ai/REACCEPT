@Test
    public void testWhenComplete() {
        boolean result = Task.supplyAsync(() -> {
            throw new IllegalStateException();
        }).whenComplete(exception -> {
            Assert.assertTrue(exception instanceof IllegalStateException);
        }).test();

        Assert.assertFalse("Task should fail at this case", result);
    }