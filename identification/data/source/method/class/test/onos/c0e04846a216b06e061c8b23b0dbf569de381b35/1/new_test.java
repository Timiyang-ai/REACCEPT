@Test
    public void addPackageInfoTest() throws IOException {

        File dirPath = new File(CREATE_PATH);
        dirPath.mkdirs();
        addPackageInfo(dirPath, CHECK1, CREATE_PATH, false);
        File filePath = new File(dirPath + File.separator + PKG_INFO);
        assertThat(filePath.isFile(), is(true));
    }