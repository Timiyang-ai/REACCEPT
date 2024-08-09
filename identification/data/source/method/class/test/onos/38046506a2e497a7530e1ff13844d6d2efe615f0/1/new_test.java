@Test
    public void updateFileHandleTest() throws IOException {

        File dir = new File(BASE_PKG + File.separator + "File1");
        dir.mkdirs();
        File createFile = new File(dir + "testFile");
        createFile.createNewFile();
        File createSourceFile = new File(dir + "sourceTestFile");
        createSourceFile.createNewFile();
        FileSystemUtil.updateFileHandle(createFile, "This is to append a text to the file first1\n", false);
        FileSystemUtil.updateFileHandle(createFile, "This is next second line\n", false);
        FileSystemUtil.updateFileHandle(createFile, "This is next third line in the file", false);
        FileSystemUtil.appendFileContents(createFile, createSourceFile);
        FileSystemUtil.updateFileHandle(createFile, null, true);
    }