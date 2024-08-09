public FileInfo getFileInfo(final long fileId) throws IOException {
    return retryRPC(() -> GrpcUtils.fromProto(mClient
        .getFileInfo(GetFileInfoPRequest.newBuilder().setFileId(fileId).build()).getFileInfo()));
  }