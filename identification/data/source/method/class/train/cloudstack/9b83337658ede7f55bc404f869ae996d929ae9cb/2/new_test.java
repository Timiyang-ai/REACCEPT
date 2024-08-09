    @Test
    public void base64StringToFileTest() throws Exception {
        String encodedIsoData = "Y29udGVudA==";

        String parentFolder = "parentFolder";
        String fileName = "fileName";

        File parentFolderFile = new File(parentFolder);
        parentFolderFile.mkdir();

        ConfigDriveBuilder.base64StringToFile(encodedIsoData, parentFolder, fileName);

        File file = new File(parentFolderFile, fileName);
        String contentOfFile = new String(FileUtils.readFileToByteArray(file), StandardCharsets.US_ASCII);

        Assert.assertEquals("content", contentOfFile);

        file.delete();
        parentFolderFile.delete();
    }