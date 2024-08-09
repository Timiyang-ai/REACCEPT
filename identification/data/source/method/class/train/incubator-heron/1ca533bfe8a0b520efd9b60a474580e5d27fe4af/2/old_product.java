public boolean killTopology() {

    // Setup connection
    String deploymentURI = String.format(
        "%s/api/v1/namespaces/default/pods?labelSelector=topology%%3D%s",
        this.kubernetesURI,
        this.topologyName);

    LOG.log(Level.INFO, deploymentURI);
    HttpURLConnection conn = NetworkUtils.getHttpConnection(deploymentURI);
    if (conn == null) {
      LOG.log(Level.SEVERE, "Failed to find k8s deployment API");
      return false;
    }

    try {
      if (!NetworkUtils.sendHttpDeleteRequest(conn)) {
        LOG.log(Level.SEVERE, "Failed to send delete request to k8s deployment API");
        return false;
      }

      // check response
      boolean success = NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_OK);

      if (success) {
        LOG.log(Level.SEVERE, "Successfully killed topology deployments");
        return true;
      } else {
        LOG.log(Level.SEVERE, "Failure to delete topology deployments");
        return false;
      }

    } finally {
      // Disconnect to release resources
      conn.disconnect();
    }


  }