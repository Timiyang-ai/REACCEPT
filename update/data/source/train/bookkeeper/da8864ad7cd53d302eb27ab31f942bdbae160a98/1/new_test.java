@Test(expected = DiskOutOfSpaceException.class)
    public void testCheckDiskFull() throws IOException {
        File file = File.createTempFile("DiskCheck", "test");
        long usableSpace = file.getUsableSpace();
        long totalSpace = file.getTotalSpace();
        float threshold =
                (1f - ((float) usableSpace / (float) totalSpace)) - 0.05f;
        diskChecker.setDiskSpaceThreshold(threshold, threshold);
        diskChecker.checkDiskFull(file);
    }