    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void deleteUpload() throws IOException {
        // Create data set including files
        final DataSet dataSet = createDataSet();

        final File file = new File(datasetsFolder.newFolder(dataSet.getId()), "test-file.txt");
        file.createNewFile();
        Assert.assertTrue("Expected file to exist", file.exists());

        // Test deleting a file
        final CatalogFileManager fileManager = new MockCatalogFileManager();
        fileManager.deleteUpload(dataSet, "test-file.txt");
        Assert.assertFalse("Expected file to be deleted", file.exists());
    }