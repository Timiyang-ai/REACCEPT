@Test
    public void testListLogFiles() throws IOException {
        FileAttribute[] attrs = new FileAttribute[0];
        String rootPath = Files.createTempDirectory("workers-artifacts", attrs).toFile().getCanonicalPath();
        File file1 = new File(String.join(File.separator, rootPath, "topoA", "1111"), "worker.log");
        File file2 = new File(String.join(File.separator, rootPath, "topoA", "2222"), "worker.log");
        File file3 = new File(String.join(File.separator, rootPath, "topoB", "1111"), "worker.log");

        file1.getParentFile().mkdirs();
        file2.getParentFile().mkdirs();
        file3.getParentFile().mkdirs();
        file1.createNewFile();
        file2.createNewFile();
        file3.createNewFile();

        String origin = "www.origin.server.net";
        Map<String, Object> stormConf = Utils.readStormConfig();
        LogviewerLogPageHandler handler = new LogviewerLogPageHandler(rootPath, null,
                new WorkerLogs(stormConf, new File(rootPath)), new ResourceAuthorizer(stormConf));

        final Response expectedAll = LogviewerResponseBuilder.buildSuccessJsonResponse(
                Lists.newArrayList("topoA/port1/worker.log", "topoA/port2/worker.log", "topoB/port1/worker.log"),
                null,
                origin
        );

        final Response expectedFilterPort = LogviewerResponseBuilder.buildSuccessJsonResponse(
                Lists.newArrayList("topoA/port1/worker.log", "topoB/port1/worker.log"),
                null,
                origin
        );

        final Response expectedFilterTopoId = LogviewerResponseBuilder.buildSuccessJsonResponse(
                Lists.newArrayList("topoB/port1/worker.log"),
                null,
                origin
        );

        final Response returnedAll = handler.listLogFiles("user", null, null, null, origin);
        final Response returnedFilterPort = handler.listLogFiles("user", 1111, null, null, origin);
        final Response returnedFilterTopoId = handler.listLogFiles("user", null, "topoB", null, origin);

        Utils.forceDelete(rootPath);

        assertEqualsJsonResponse(expectedAll, returnedAll, List.class);
        assertEqualsJsonResponse(expectedFilterPort, returnedFilterPort, List.class);
        assertEqualsJsonResponse(expectedFilterTopoId, returnedFilterTopoId, List.class);
    }