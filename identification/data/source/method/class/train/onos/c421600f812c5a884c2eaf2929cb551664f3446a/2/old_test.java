@Test
    public void createSourceFilesTest() throws IOException {

        FileSystemUtil.createSourceFiles(baseDirPkg + "srcFile1", packageInfoContent, GeneratedFileType.INTERFACE);
    }