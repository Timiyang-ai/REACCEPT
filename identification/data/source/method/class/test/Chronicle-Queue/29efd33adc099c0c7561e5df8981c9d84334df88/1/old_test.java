    @Test
    public void state() throws IOException {
        assumeFalse(OS.isWindows());
        final Path dir = Files.createTempDirectory("openByAnyProcess");
        try {
            final File testFile =  dir.resolve("tmpFile").toFile();
            Files.write(testFile.toPath(), "A".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            // The file is created but not open
            assertEquals(FileState.CLOSED, FileUtil.state(testFile));

            try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
                // The file is now held open
                assertEquals(FileState.OPEN, FileUtil.state(testFile));
            };

            // The file is now released again
            assertEquals(FileState.CLOSED, FileUtil.state(testFile));

        } finally {
            deleteDir(dir.toFile());
        }
    }