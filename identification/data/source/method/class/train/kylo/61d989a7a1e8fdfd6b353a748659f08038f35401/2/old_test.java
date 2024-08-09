    @Test
    public void addFile() {
        // Mock Spark context
        final SparkContext sparkContext = Mockito.mock(SparkContext.class);
        Mockito.when(sparkContext.hadoopConfiguration()).thenReturn(new Configuration(false));

        // Test adding null file
        final DataSourceResourceLoader loader = DataSourceResourceLoader.create(sparkContext);
        loader.addFile(null);
        Mockito.verify(sparkContext, Mockito.never()).addFile(Mockito.anyString());

        // Test adding missing file
        loader.addFile("file:/tmp/" + UUID.randomUUID().toString());
        Mockito.verify(sparkContext, Mockito.never()).addFile(Mockito.anyString());

        // Test adding new file
        final String filePath = getClass().getResource("DataSourceResourceLoaderTest.class").toString();
        loader.addFile(filePath);
        loader.addFile(filePath);  // de-duplication check
        Mockito.verify(sparkContext, Mockito.times(1)).addFile(filePath);
    }