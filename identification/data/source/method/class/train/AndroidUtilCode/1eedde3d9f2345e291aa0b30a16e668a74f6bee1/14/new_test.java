    @Test
    public void zipFiles() throws Exception {
        List<String> files = new ArrayList<>();
        files.add(PATH_ZIP + "test.txt");
        files.add(PATH_ZIP);
        files.add(PATH_ZIP + "testDir");
        assertTrue(ZipUtils.zipFiles(files, zipFiles));
    }