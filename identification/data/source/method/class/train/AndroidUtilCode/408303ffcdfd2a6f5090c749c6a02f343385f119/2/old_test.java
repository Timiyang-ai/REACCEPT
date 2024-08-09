    @Test
    public void listFilesInDir() {
        System.out.println(FileUtils.listFilesInDir(PATH_FILE, false).toString());
        System.out.println(FileUtils.listFilesInDir(PATH_FILE, true).toString());
    }