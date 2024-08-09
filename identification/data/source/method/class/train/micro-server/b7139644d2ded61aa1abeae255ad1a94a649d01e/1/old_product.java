public FileInputStream getFileInputStream(String bucketName, String key, Supplier<File> localFileSupplier)
        throws AmazonClientException, InterruptedException, IOException {
        File file = localFileSupplier.get();
        Download download = transferManager.download(bucketName, key, file);
        download.waitForCompletion();
        return new FileInputStream(file);
    }