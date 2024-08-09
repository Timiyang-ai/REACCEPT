public InputStream getFileInputStream(String bucketName, String key)
        throws AmazonClientException, InterruptedException, IOException {
        File file = createTmpFile();

        Download download = transferManager.download(bucketName, key, file);
        download.waitForCompletion();
        return Files.newInputStream(file.toPath(), StandardOpenOption.DELETE_ON_CLOSE);
    }