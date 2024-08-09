    @Test
    public void listUploads() throws IOException {
        // Create data set including files
        final DataSet dataSet = createDataSet();
        final File dataSetFolder = datasetsFolder.newFolder(dataSet.getId());

        Files.write("data1", new File(dataSetFolder, "file1.txt"), StandardCharsets.UTF_8);
        Files.write("data2", new File(dataSetFolder, "file2.txt"), StandardCharsets.UTF_8);
        Files.write("data3", new File(dataSetFolder, "file3.txt"), StandardCharsets.UTF_8);

        // Test listing files
        final CatalogFileManager fileManager = new MockCatalogFileManager();
        final List<DataSetFile> files = fileManager.listUploads(dataSet);
        Assert.assertThat(files, CoreMatchers.hasItem(equalTo("file1.txt", new Path(dataSetFolder.toPath().resolve("file1.txt").toUri()).toString(), false, 5, "data1")));
        Assert.assertThat(files, CoreMatchers.hasItem(equalTo("file2.txt", new Path(dataSetFolder.toPath().resolve("file2.txt").toUri()).toString(), false, 5, "data2")));
        Assert.assertThat(files, CoreMatchers.hasItem(equalTo("file3.txt", new Path(dataSetFolder.toPath().resolve("file3.txt").toUri()).toString(), false, 5, "data3")));
        Assert.assertEquals(3, files.size());
    }