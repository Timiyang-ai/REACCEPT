public boolean restartApp(int appId) {
    if (appId == -1) {
      // TODO (nlu): implement restart all
      String message = "Restarting the whole topology is not supported yet. "
          + "Please kill and resubmit the topology.";
      LOG.log(Level.SEVERE, message);
      return false;
    }

    // Setup Connection
    String restartRequest = String.format("%s/v2/apps/%s/%d/restart",
        this.marathonURI, this.topologyName, appId);
    HttpURLConnection conn = NetworkUtils.getHttpConnection(restartRequest);

    if (this.marathonAuthToken != null) {
      conn.setRequestProperty("Authorization", String.format("token=%s", this.marathonAuthToken));
    }

    if (conn == null) {
      LOG.log(Level.SEVERE, "Failed to find marathon scheduler");
      return false;
    }

    try {
      // send post request to restart app
      byte[] empty = new byte[0];
      if (!NetworkUtils.sendHttpPostRequest(conn, NetworkUtils.JSON_TYPE, empty)) {
        LOG.log(Level.SEVERE, "Failed to send post request");
        return false;
      }

      // Check response
      boolean success = NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_OK);

      if (success) {
        LOG.log(Level.INFO, "Successfully restarted container {0}", appId);
        return true;
      } else if (NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_UNAUTHORIZED)) {
        LOG.log(Level.SEVERE, "Marathon requires authentication");
        return false;
      } else {
        LOG.log(Level.SEVERE, "Failed to restart container {0}", appId);
        return false;
      }
    } finally {
      // Disconnect to release resources
      conn.disconnect();
    }
  }