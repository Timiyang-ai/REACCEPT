    @Test
    public void newFuture() {
        DefaultFuture future = defaultFuture(3000);
        Assertions.assertNotNull(future, "new future return null");
    }