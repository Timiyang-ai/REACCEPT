@Test
    public void insertStringInFileTest() throws IOException {
        File dir = new File(baseDir + File.separator + "File1");
        dir.mkdirs();
        File createFile = new File(dir + "testFile");
        createFile.createNewFile();
        File createSourceFile = new File(dir + "sourceTestFile");
        createSourceFile.createNewFile();
        FileSystemUtil.insertStringInFile(createFile, "This is to append a text to the file first1\n");
        FileSystemUtil.insertStringInFile(createFile, "This is next second line\n");
        FileSystemUtil.insertStringInFile(createFile, "This is next third line in the file");
        FileSystemUtil.appendFileContents(createFile, createSourceFile);
    }