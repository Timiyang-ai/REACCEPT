    @Test
    public void writeFileTest() throws Exception {
        PowerMockito.mockStatic(FileUtils.class);

        ConfigDriveBuilder.writeFile(new File("folder"), "subfolder", "content");

        PowerMockito.verifyStatic();
        FileUtils.write(Mockito.any(File.class), Mockito.anyString(), Mockito.any(Charset.class), Mockito.eq(false));
    }