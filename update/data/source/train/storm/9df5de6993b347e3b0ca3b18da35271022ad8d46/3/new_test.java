@Test
    public void testGetDeadWorkerDirs() throws Exception {
        Map<String, Object> stormConf = Utils.readStormConfig();
        stormConf.put(SUPERVISOR_WORKER_TIMEOUT_SECS, 5);

        LSWorkerHeartbeat hb = new LSWorkerHeartbeat();
        hb.set_time_secs(1);

        Map<String, LSWorkerHeartbeat> idToHb = Collections.singletonMap("42", hb);
        int nowSecs = 2;
        try (TmpPath testDir = new TmpPath()) {
            Path unexpectedDir1 = createDir(testDir.getFile().toPath(), "dir1");
            Path expectedDir2 = createDir(testDir.getFile().toPath(), "dir2");
            Path expectedDir3 = createDir(testDir.getFile().toPath(), "dir3");
            Set<Path> logDirs = Sets.newSet(unexpectedDir1, expectedDir2, expectedDir3);
            SupervisorUtils mockedSupervisorUtils = mock(SupervisorUtils.class);
            SupervisorUtils.setInstance(mockedSupervisorUtils);

            Map<String, Object> conf = Utils.readStormConfig();
            StormMetricsRegistry metricRegistry = new StormMetricsRegistry();
            WorkerLogs stubbedWorkerLogs = new WorkerLogs(conf, null, metricRegistry) {
                @Override
                public SortedSet<Path> getLogDirs(Set<Path> logDirs, Predicate<String> predicate) {
                    TreeSet<Path> ret = new TreeSet<>();
                    if (predicate.test("42")) {
                        ret.add(unexpectedDir1);
                    }
                    if (predicate.test("007")) {
                        ret.add(expectedDir2);
                    }
                    if (predicate.test("")) {
                        ret.add(expectedDir3);
                    }

                    return ret;
                }
            };

            LogCleaner logCleaner = new LogCleaner(conf, stubbedWorkerLogs, new DirectoryCleaner(metricRegistry), null, metricRegistry);

            when(mockedSupervisorUtils.readWorkerHeartbeatsImpl(anyMap())).thenReturn(idToHb);
            assertEquals(Sets.newSet(expectedDir2, expectedDir3), logCleaner.getDeadWorkerDirs(nowSecs, logDirs));
        } finally {
            SupervisorUtils.resetInstance();
        }
    }