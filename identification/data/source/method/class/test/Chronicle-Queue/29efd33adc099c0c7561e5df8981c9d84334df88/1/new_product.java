public static FileState state(@NotNull File file) {
        assertOsSupported();
        if (!file.exists()) return FileState.NON_EXISTENT;
        final String absolutePath = file.getAbsolutePath();
        try {
            final Process process = new ProcessBuilder(new String[]{"lsof", "|", "grep", absolutePath}).start();
            try  (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                return reader.lines()
                    .anyMatch(l -> l.contains(absolutePath))
                    ? FileState.OPEN
                    : FileState.CLOSED;
            } finally {
                process.destroyForcibly();
            }
        } catch(IOException ignored) {
            // Do nothing
        }
        return FileState.UNDETERMINED;
    }