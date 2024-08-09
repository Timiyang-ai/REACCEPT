public boolean submitTopology(String appConf) {
    if (this.isVerbose) {
      LOG.log(Level.INFO, "Topology conf is: " + appConf);
    }

    // Marathon atleast till 1.4.x does not allow upper case jobids
    if (!this.topologyName.equals(this.topologyName.toLowerCase())) {
      LOG.log(Level.SEVERE, "Marathon scheduler does not allow upper case topologies");
      return false;
    }

    // Setup Connection
    String schedulerURI = String.format("%s/v2/groups", this.marathonURI);
    HttpURLConnection conn = NetworkUtils.getHttpConnection(schedulerURI);
    if (conn == null) {
      LOG.log(Level.SEVERE, "Failed to find marathon scheduler");
      return false;
    }

    try {
      // Send post request with marathon conf for topology
      if (!NetworkUtils.sendHttpPostRequest(conn, NetworkUtils.JSON_TYPE, appConf.getBytes())) {
        LOG.log(Level.SEVERE, "Failed to send post request");
        return false;
      }

      // Check response
      boolean success = NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_CREATED);

      if (success) {
        LOG.log(Level.INFO, "Topology submitted successfully");
        return true;
      } else {
        LOG.log(Level.SEVERE, "Failed to submit topology");
        return false;
      }
    } finally {
      // Disconnect to release resources
      conn.disconnect();
    }
  }