protected static OutputStream newLogStream(Path path) throws IOException {
        Path usedPath = path;
        Path lockFile = null;
        FileChannel lockFileChannel = null;
        for (int unique = 0;; unique++) {
            StringBuilder lockFileNameBuilder = new StringBuilder();
            lockFileNameBuilder.append(path.toString());
            if (unique > 0) {
                lockFileNameBuilder.append(unique);
                usedPath = Paths.get(lockFileNameBuilder.toString());
            }
            lockFileNameBuilder.append(".lck");
            lockFile = Paths.get(lockFileNameBuilder.toString());
            Map.Entry<FileChannel, Boolean> openResult = openChannel(lockFile);
            if (openResult != null) {
                lockFileChannel = openResult.getKey();
                if (lock(lockFileChannel, openResult.getValue())) {
                    break;
                } else {
                    // Close and try next name
                    lockFileChannel.close();
                }
            }
        }
        assert lockFile != null && lockFileChannel != null;
        boolean success = false;
        try {
            OutputStream stream = new LockableOutputStream(
                            new BufferedOutputStream(Files.newOutputStream(usedPath, WRITE, CREATE, APPEND)),
                            lockFile,
                            lockFileChannel);
            success = true;
            return stream;
        } finally {
            if (!success) {
                LockableOutputStream.unlock(lockFile, lockFileChannel);
            }
        }
    }