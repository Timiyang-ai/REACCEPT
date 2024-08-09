    @Test
    @SneakyThrows
    public void getFileInputStream() {
        TransferManager transferManager = mock(TransferManager.class);
        Download download = mock(Download.class);
        when(transferManager.download(anyString(), anyString(), any())).thenReturn(download);

        ReadUtils readUtils = new ReadUtils(transferManager,System.getProperty("java.io.tmpdir"));

        InputStream fileInputStream = readUtils.getFileInputStream("bucket", "key");
        assertNotNull(fileInputStream);

        verify(transferManager, times(1)).download(anyString(), anyString(), any(File.class));
        verify(download, times(1)).waitForCompletion();

        fileInputStream.close();
    }