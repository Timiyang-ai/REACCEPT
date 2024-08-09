private void handleGetReplicasForBlobId(MessageInfo messageInfo, AdminExecutionData executionData)
      throws RestServiceException {
    logger.trace("Handling getReplicas");
    try {
      RestResponseHandler responseHandler = messageInfo.getResponseHandler();
      if (messageInfo.getRestObject() instanceof RestRequest) {
        //TODO: Reconsider this model of execution
        TaskExecutor executor = new GetReplicasForBlobIdExecutor(clusterMap);
        String replicaStr = executor.execute(executionData).toString();
        if (replicaStr != null) {
          responseHandler.setContentType("application/json");
          responseHandler.finalizeResponse();
          responseHandler.addToBodyAndFlush(replicaStr.getBytes(), true);
        } else {
          throw new RestServiceException("Did not get a result for the GetReplicasForBlobId operation",
              RestServiceErrorCode.ResponseBuildingFailure);
        }
      } else {
        responseHandler.close();
      }
    } finally {
      messageInfo.getRestObject().release();
    }
  }