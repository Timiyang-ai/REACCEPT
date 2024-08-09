    @Test
    public void fileToBase64StringTest() throws Exception {
        PowerMockito.mockStatic(FileUtils.class);

        String fileContent = "content";
        Method method = getFileUtilsReadfileToByteArrayMethod();
        PowerMockito.when(FileUtils.class, method).withArguments(Mockito.any(File.class)).thenReturn(fileContent.getBytes());

        String returnedContentInBase64 = ConfigDriveBuilder.fileToBase64String(new File("file"));

        Assert.assertEquals("Y29udGVudA==", returnedContentInBase64);
    }