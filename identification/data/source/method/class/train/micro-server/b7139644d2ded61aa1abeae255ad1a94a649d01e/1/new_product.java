public InputStream getFileInputStream(String bucketName, String key)
        throws AmazonClientException, InterruptedException, IOException {
        File file = Files.createTempFile(getDefault().getPath(tmpDirectory), "micro-s3", "file").toFile();

        Download download = transferManager.download(bucketName, key, file);
        download.waitForCompletion();
        return Files.newInputStream(file.toPath(), StandardOpenOption.DELETE_ON_CLOSE);
    }