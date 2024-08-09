@Override
  public void handleGet(RestRequestInfo restRequestInfo)
      throws RestServiceException {
    RestRequestMetadata restRequestMetadata = restRequestInfo.getRestRequestMetadata();
    logger.trace("Handling get restRequestMetadata - " + restRequestMetadata.getUri());
    AdminOperationType operationType = getOperationType(restRequestMetadata);
    switch (operationType) {
      case echo:
        EchoHandler.handleRequest(restRequestInfo);
        break;
      case getReplicasForBlobId:
        GetReplicasForBlobIdHandler.handleRequest(restRequestInfo, clusterMap);
        break;
      default:
        // TODO: this is probably a blob get
        throw new IllegalStateException("Blob GET not implemented");
    }
  }