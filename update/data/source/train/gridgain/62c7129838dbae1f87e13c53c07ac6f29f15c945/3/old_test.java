@Test
    public void testServerClientMessage() throws Exception {
        if (!testsCfg.withClients())
            return;

        runInAllDataModes(new TestRunnable() {
            @Override public void run() throws Exception {
                serverClientMessage(false);
            }
        });
    }