@Test(expected = DiskOutOfSpaceException.class)
    public void testCheckDiskFull() throws IOException {
        File file = File.createTempFile("DiskCheck", "test");
        long usableSpace = file.getUsableSpace();
        long totalSpace = file.getTotalSpace();
        diskChecker
                .setDiskSpaceThreshold((1f - ((float) usableSpace / (float) totalSpace)) - 0.05f);
        diskChecker.checkDiskFull(file);
    }