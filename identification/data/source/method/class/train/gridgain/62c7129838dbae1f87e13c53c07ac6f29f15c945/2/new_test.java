@Test
    public void testClientServerMessage() throws Exception {
        if (!testsCfg.withClients())
            return;

        runInAllDataModes(new TestRunnable() {
            @Override public void run() throws Exception {
                clientServerMessage();
            }
        });
    }