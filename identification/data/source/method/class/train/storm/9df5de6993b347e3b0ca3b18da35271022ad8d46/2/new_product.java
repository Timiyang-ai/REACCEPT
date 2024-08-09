@VisibleForTesting
    Matched findNMatches(List<Path> logs, int numMatches, int fileOffset, int startByteOffset, String targetStr) {
        logs = drop(logs, fileOffset);
        LOG.debug("{} files to scan", logs.size());

        List<Map<String, Object>> matches = new ArrayList<>();
        int matchCount = 0;
        int scannedFiles = 0;

        while (true) {
            if (logs.isEmpty()) {
                //fileOffset = one past last scanned file
                break;
            }

            Path firstLog = logs.get(0);
            Map<String, Object> matchInLog;
            try {
                LOG.debug("Looking through {}", firstLog);
                matchInLog = substringSearch(firstLog, targetStr, numMatches - matchCount, startByteOffset);
                scannedFiles++;
            } catch (InvalidRequestException e) {
                LOG.error("Can't search past end of file.", e);
                matchInLog = new HashMap<>();
            }

            String fileName = WorkerLogs.getTopologyPortWorkerLog(firstLog);

            //This section simply put the formatted log filename and corresponding port in the matching.
            final List<Map<String, Object>> newMatches = new ArrayList<>(matches);
            Map<String, Object> currentFileMatch = new HashMap<>(matchInLog);
            currentFileMatch.put("fileName", fileName);
            Path firstLogAbsPath = firstLog.toAbsolutePath().normalize();
            currentFileMatch.put("port", truncatePathToLastElements(firstLogAbsPath, 2).getName(0).toString());
            newMatches.add(currentFileMatch);

            int newCount = matchCount + ((List<?>)matchInLog.getOrDefault("matches", Collections.emptyList())).size();
            if (newCount == matchCount) {
                // matches and matchCount is not changed
                logs = rest(logs);
                startByteOffset = 0;
                fileOffset = fileOffset + 1;
            } else if (newCount >= numMatches) {
                matches = newMatches;
                //fileOffset = the index of last scanned file
                break;
            } else {
                matches = newMatches;
                logs = rest(logs);
                startByteOffset = 0;
                fileOffset = fileOffset + 1;
                matchCount = newCount;
            }
        }

        LOG.debug("scanned {} files", scannedFiles);
        return new Matched(fileOffset, targetStr, matches, scannedFiles);
    }