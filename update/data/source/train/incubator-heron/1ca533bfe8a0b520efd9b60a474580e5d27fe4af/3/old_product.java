public boolean submitTopology(String[] appConfs) {

    if (!this.topologyName.equals(this.topologyName.toLowerCase())) {
      LOG.log(Level.SEVERE, "K8s scheduler does not allow upper case topologies");
      return false;
    }

    String deploymentURI = String.format(
        "%s/api/v1/namespaces/default/pods",
        this.kubernetesURI);

    boolean allSuccessful = true;

    for (int i = 0; i < appConfs.length; i++) {
      LOG.log(Level.INFO, "Topology configuration is: " + appConfs[i]);

      // Get a connection
      HttpURLConnection conn = NetworkUtils.getHttpConnection(deploymentURI);
      if (conn == null) {
        LOG.log(Level.SEVERE, "Fauled to find k8s deployment API");
        return false;
      }

      try {
        // send post request with json body for the topology
        if (!NetworkUtils.sendHttpPostRequest(conn,
                                              NetworkUtils.JSON_TYPE,
                                              appConfs[i].getBytes())) {
          LOG.log(Level.SEVERE, "Failed to send post to k8s deployment api");
          allSuccessful = false;
          break;
        }

        // check the response
        boolean success = NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_CREATED);

        if (success) {
          LOG.log(Level.INFO, "Topology Deployment submitted to k8s deployment API successfully");
        } else {
          LOG.log(Level.SEVERE, "Failed to submit Deployment to k8s");
          byte[] bytes = NetworkUtils.readHttpResponse(conn);
          LOG.log(Level.INFO, Arrays.toString(bytes));
          allSuccessful = false;
          break;
        }
      } finally {
        conn.disconnect();
      }

    }


    return allSuccessful;
  }