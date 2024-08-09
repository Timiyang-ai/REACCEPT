@Test
    public void testIdentifyWorkerLogDirs() throws Exception {
        File port1Dir = new MockDirectoryBuilder().setDirName("/workers-artifacts/topo1/port1").build();
        File mockMetaFile = new MockFileBuilder().setFileName("worker.yaml").build();

        String expId = "id12345";
        Map<String, File> expected = Collections.singletonMap(expId, port1Dir);

        try {
            SupervisorUtils mockedSupervisorUtils = mock(SupervisorUtils.class);
            SupervisorUtils.setInstance(mockedSupervisorUtils);

            Map<String, Object> stormConf = Utils.readStormConfig();
            WorkerLogs workerLogs = new WorkerLogs(stormConf, port1Dir, new StormMetricsRegistry()) {
                @Override
                public Optional<File> getMetadataFileForWorkerLogDir(File logDir) throws IOException {
                    return Optional.of(mockMetaFile);
                }

                @Override
                public String getWorkerIdFromMetadataFile(String metaFile) {
                    return expId;
                }
            };

            when(mockedSupervisorUtils.readWorkerHeartbeatsImpl(anyMapOf(String.class, Object.class))).thenReturn(null);
            assertEquals(expected, workerLogs.identifyWorkerLogDirs(Collections.singleton(port1Dir)));
        } finally {
            SupervisorUtils.resetInstance();
        }
    }