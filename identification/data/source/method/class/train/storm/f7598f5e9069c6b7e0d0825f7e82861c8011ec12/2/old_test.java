    @Test
    public void setUpContext_allNull() {
        ShellMsg msg = mockMsg();
        logHandler.setUpContext(null, null, null);
        logHandler.log(msg);
        verify(msg).getMsg();
    }