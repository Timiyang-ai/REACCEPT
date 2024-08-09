    @Test(expected = NullPointerException.class)
    public void getService_whenNullName() {
        nodeEngine.getService(null);
    }