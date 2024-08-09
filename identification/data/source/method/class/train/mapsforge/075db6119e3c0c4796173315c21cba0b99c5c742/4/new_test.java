    @Test
    public void mainTest() {
        String[] args = new String[]{};
        verifyInvalidArguments(args);

        args = new String[]{"file/not/found.map"};
        verifyInvalidArguments(args);
    }