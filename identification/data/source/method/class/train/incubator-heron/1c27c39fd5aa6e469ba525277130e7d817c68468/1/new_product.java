protected boolean requestSchedulerService(Command command, byte[] data) {
    String endpoint = getCommandEndpoint(schedulerHttpEndpoint, command);
    final HttpURLConnection connection = NetworkUtils.getHttpConnection(endpoint);
    if (connection == null) {
      LOG.severe("Scheduler not found.");
      return false;
    }

    // now, we have a valid connection
    try {
      // send the actual http request
      if (!NetworkUtils.sendHttpPostRequest(connection, NetworkUtils.URL_ENCODE_TYPE, data)) {
        LOG.log(Level.SEVERE, "Failed to send http request to scheduler");
        return false;
      }

      // receive the response for manage topology
      Common.StatusCode statusCode;

      LOG.fine("Receiving response from scheduler...");
      try {
        statusCode = Scheduler.SchedulerResponse.newBuilder()
            .mergeFrom(NetworkUtils.readHttpResponse(connection))
            .build().getStatus().getStatus();
      } catch (InvalidProtocolBufferException e) {
        LOG.log(Level.SEVERE, "Failed to parse response", e);
        return false;
      }

      if (!statusCode.equals(Common.StatusCode.OK)) {
        LOG.severe("Received not OK response from scheduler");
        return false;
      }
    } finally {
      connection.disconnect();
    }

    return true;
  }