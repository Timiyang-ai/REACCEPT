@Override
  @Deprecated
  public LoadMetadataTResponse loadMetadata(final String alluxioPath, final boolean recursive,
      final LoadMetadataTOptions options)
      throws AlluxioTException {
    return RpcUtils.call(LOG, new RpcCallableThrowsIOException<LoadMetadataTResponse>() {
      @Override
      public LoadMetadataTResponse call() throws AlluxioException, IOException {
        return new LoadMetadataTResponse(mFileSystemMaster.loadMetadata(new AlluxioURI(alluxioPath),
            LoadMetadataOptions.defaults().setCreateAncestors(true).setLoadDescendantLevels(1)));
      }

      @Override
      public String toString() {
        return String.format("LoadMetadata: alluxioPath=%s, recursive=%s, options=%s", alluxioPath,
            recursive, options);
      }
    });
  }