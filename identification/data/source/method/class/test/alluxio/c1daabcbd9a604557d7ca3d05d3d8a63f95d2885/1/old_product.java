public static GrpcDataWriter create(FileSystemContext context, WorkerNetAddress address,
      long id, long length, RequestType type, OutStreamOptions options)
      throws IOException {
    AlluxioConfiguration conf = context.getConf();
    long chunkSize = conf.getBytes(PropertyKey.USER_NETWORK_WRITER_CHUNK_SIZE_BYTES);
    BlockWorkerClient grpcClient = context.acquireBlockWorkerClient(address);
    try {
      return new GrpcDataWriter(context, address, id, length, chunkSize, type, options,
          grpcClient);
    } catch (Exception e) {
      context.releaseBlockWorkerClient(address, grpcClient);
      throw e;
    }
  }