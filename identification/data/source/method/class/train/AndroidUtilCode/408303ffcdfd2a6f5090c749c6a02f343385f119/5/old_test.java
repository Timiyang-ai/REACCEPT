    @Test
    public void listFilesInDirWithFilter() {
        System.out.println(FileUtils.listFilesInDirWithFilter(PATH_FILE, mFilter, false).toString());
        System.out.println(FileUtils.listFilesInDirWithFilter(PATH_FILE, mFilter, true).toString());
    }