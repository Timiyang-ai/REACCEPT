    @Test
    public void createUpload() throws IOException {
        final DataSet dataSet = createDataSet();
        final String src = "Hello world!";

        // Test uploading a file
        final CatalogFileManager fileManager = new MockCatalogFileManager();
        final DataSetFile upload = fileManager.createUpload(dataSet, "file-upload.txt", new ByteArrayInputStream(src.getBytes(StandardCharsets.UTF_8)));

        final File file = datasetsFolder.getRoot().toPath().resolve(dataSet.getId()).resolve("file-upload.txt").toFile();
        Assert.assertFalse("Expected uploaded file to not be a directory", upload.isDirectory());
        Assert.assertEquals(src.length(), upload.getLength().longValue());
        Assert.assertEquals("file-upload.txt", upload.getName());
        Assert.assertEquals(file.toURI(), URI.create(upload.getPath()));
        Assert.assertEquals(src, Files.toString(file, StandardCharsets.UTF_8));
    }