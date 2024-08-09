@Test
    public void testClientClientMessage() throws Exception {
        if (!testsCfg.withClients())
            return;

        runInAllDataModes(new TestRunnable() {
            @Override public void run() throws Exception {
                clientClientMessage();
            }
        });
    }