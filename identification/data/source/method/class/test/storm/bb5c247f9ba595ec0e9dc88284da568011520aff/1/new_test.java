@Test
    public void testGetDeadWorkerDirs() throws Exception {
        Map<String, Object> stormConf = Utils.readStormConfig();
        stormConf.put(SUPERVISOR_WORKER_TIMEOUT_SECS, 5);

        LSWorkerHeartbeat hb = new LSWorkerHeartbeat();
        hb.set_time_secs(1);

        Map<String, LSWorkerHeartbeat> idToHb = Collections.singletonMap("42", hb);
        int nowSecs = 2;
        File unexpectedDir1 = new MockDirectoryBuilder().setDirName("dir1").build();
        File expectedDir2 = new MockDirectoryBuilder().setDirName("dir2").build();
        File expectedDir3 = new MockDirectoryBuilder().setDirName("dir3").build();
        Set<File> logDirs = Sets.newSet(unexpectedDir1, expectedDir2, expectedDir3);

        try {
            SupervisorUtils mockedSupervisorUtils = mock(SupervisorUtils.class);
            SupervisorUtils.setInstance(mockedSupervisorUtils);

            Map<String, Object> conf = Utils.readStormConfig();
            WorkerLogs stubbedWorkerLogs = new WorkerLogs(conf, null) {
                @Override
                public Map<String, File> identifyWorkerLogDirs(Set<File> logDirs) {
                    Map<String, File> ret = new HashMap<>();
                    ret.put("42", unexpectedDir1);
                    ret.put("007", expectedDir2);
                    // this tests a directory with no yaml file thus no worker id
                    ret.put("", expectedDir3);

                    return ret;
                }
            };

            LogCleaner logCleaner = new LogCleaner(conf, stubbedWorkerLogs, new DirectoryCleaner(), null);

            when(mockedSupervisorUtils.readWorkerHeartbeatsImpl(anyMapOf(String.class, Object.class))).thenReturn(idToHb);
            assertEquals(Sets.newSet(expectedDir2, expectedDir3), logCleaner.getDeadWorkerDirs(nowSecs, logDirs));
        } finally {
            SupervisorUtils.resetInstance();
        }
    }