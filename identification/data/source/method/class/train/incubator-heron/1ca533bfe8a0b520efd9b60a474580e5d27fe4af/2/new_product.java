protected boolean killTopology() {

    // Setup connection
    String deploymentURI = String.format(
        "%s?labelSelector=topology%%3D%s",
        this.baseUriPath,
        this.topologyName);

    // send the delete request to the scheduler
    HttpJsonClient jsonAPIClient = new HttpJsonClient(deploymentURI);
    try {
      jsonAPIClient.delete(HttpURLConnection.HTTP_OK);
    } catch (IOException ioe) {
      LOG.log(Level.SEVERE, "Problem sending delete request: " + deploymentURI, ioe);
      return false;
    }
    return true;
  }