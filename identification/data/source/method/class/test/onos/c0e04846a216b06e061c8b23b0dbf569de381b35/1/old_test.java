@Test
    public void addPackageInfoTest() throws IOException {

        File dirPath = new File(CREATE_PATH);
        dirPath.mkdirs();
        addPackageInfo(dirPath, "check1", CREATE_PATH);
        File filePath = new File(dirPath + File.separator + "package-info.java");
        assertThat(filePath.isFile(), is(true));
    }