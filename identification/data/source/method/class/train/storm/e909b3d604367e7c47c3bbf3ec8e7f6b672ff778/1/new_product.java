public Response listLogFiles(String user, Integer port, String topologyId, String callback, String origin) throws IOException {
        List<Path> fileResults = null;
        if (topologyId == null) {
            if (port == null) {
                fileResults = workerLogs.getAllLogsForRootDir();
            } else {
                fileResults = new ArrayList<>();

                File[] logRootFiles = logRoot.toFile().listFiles();
                if (logRootFiles != null) {
                    for (File topoDir : logRootFiles) {
                        File[] topoDirFiles = topoDir.listFiles();
                        if (topoDirFiles != null) {
                            for (File portDir : topoDirFiles) {
                                if (portDir.getName().equals(port.toString())) {
                                    fileResults.addAll(directoryCleaner.getFilesForDir(portDir.toPath()));
                                }
                            }
                        }
                    }
                }
            }
        } else {
            if (port == null) {
                fileResults = new ArrayList<>();

                Path topoDir = logRoot.resolve(topologyId).toAbsolutePath().normalize();
                if (!topoDir.startsWith(logRoot)) {
                    return LogviewerResponseBuilder.buildSuccessJsonResponse(Collections.emptyList(), callback, origin);
                }
                if (topoDir.toFile().exists()) {
                    File[] topoDirFiles = topoDir.toFile().listFiles();
                    if (topoDirFiles != null) {
                        for (File portDir : topoDirFiles) {
                            fileResults.addAll(directoryCleaner.getFilesForDir(portDir.toPath()));
                        }
                    }
                }

            } else {
                File portDir = ConfigUtils.getWorkerDirFromRoot(logRoot.toString(), topologyId, port).getCanonicalFile();
                if (!portDir.getPath().startsWith(logRoot.toString())) {
                    return LogviewerResponseBuilder.buildSuccessJsonResponse(Collections.emptyList(), callback, origin);
                }
                if (portDir.exists()) {
                    fileResults = directoryCleaner.getFilesForDir(portDir.toPath());
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