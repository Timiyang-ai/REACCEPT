    @Test(expected = NullPointerException.class)
    public void getServiceOrNull_whenNullName() {
        nodeEngine.getServiceOrNull(null);
    }