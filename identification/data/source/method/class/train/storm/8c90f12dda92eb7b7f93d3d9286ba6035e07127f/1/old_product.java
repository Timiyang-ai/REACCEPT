public Response listLogFiles(String user, Integer port, String topologyId, String callback, String origin) throws IOException {
        List<File> fileResults = null;
        if (topologyId == null) {
            if (port == null) {
                fileResults = workerLogs.getAllLogsForRootDir();
            } else {
                fileResults = new ArrayList<>();

                File[] logRootFiles = new File(logRoot).listFiles();
                if (logRootFiles != null) {
                    for (File topoDir : logRootFiles) {
                        File[] topoDirFiles = topoDir.listFiles();
                        if (topoDirFiles != null) {
                            for (File portDir : topoDirFiles) {
                                if (portDir.getName().equals(port.toString())) {
                                    fileResults.addAll(DirectoryCleaner.getFilesForDir(portDir));
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (port == null) {
                fileResults = new ArrayList<>();

                File topoDir = new File(logRoot, topologyId);
                if (topoDir.exists()) {
                    File[] topoDirFiles = topoDir.listFiles();
                    if (topoDirFiles != null) {
                        for (File portDir : topoDirFiles) {
                            fileResults.addAll(DirectoryCleaner.getFilesForDir(portDir));
                        }
                    }
                }

            } else {
                File portDir = ConfigUtils.getWorkerDirFromRoot(logRoot, topologyId, port);
                if (portDir.exists()) {
                    fileResults = DirectoryCleaner.getFilesForDir(portDir);
                }
            }
        }

        List<String> files;
        if (fileResults != null) {
            files = fileResults.stream()
                    .map(WorkerLogs::getTopologyPortWorkerLog)
                    .sorted().collect(toList());
        } else {
            files = new ArrayList<>();
        }

        return LogviewerResponseBuilder.buildSuccessJsonResponse(files, callback, origin);
    }