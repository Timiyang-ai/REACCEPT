    @Test(expected = RuntimeException.class)
    public void setItems_ThrowsExceptionWithNoResource() {
        BottomBar secondBar = new BottomBar(context);
        secondBar.setItems(0);
    }