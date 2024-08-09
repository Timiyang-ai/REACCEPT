  private DataWriter create(long length) throws Exception {
    DataWriter writer =
        GrpcDataWriter.create(mContext, mAddress, BLOCK_ID, length,
            RequestType.ALLUXIO_BLOCK,
            OutStreamOptions.defaults(mClientContext).setWriteTier(TIER));
    return writer;
  }