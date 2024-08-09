public boolean killTopology() {
    // Setup Connection
    String topologyURI = String.format("%s/v2/groups/%s", this.marathonURI, this.topologyName);
    HttpURLConnection conn = NetworkUtils.getHttpConnection(topologyURI);

    // Attach a token if there is one specified
    if (this.marathonAuthToken != null) {
      conn.setRequestProperty("Authorization", String.format("token=%s", this.marathonAuthToken));
    }

    if (conn == null) {
      LOG.log(Level.SEVERE, "Failed to find marathon scheduler");
      return false;
    }

    try {
      // Send kill topology request
      if (!NetworkUtils.sendHttpDeleteRequest(conn)) {
        LOG.log(Level.SEVERE, "Failed to send delete request");
        return false;
      }

      // Check response
      boolean success = NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_OK);

      if (success) {
        LOG.log(Level.INFO, "Successfully killed topology");
        return true;
      } else if (NetworkUtils.checkHttpResponseCode(conn, HttpURLConnection.HTTP_UNAUTHORIZED)) {
        LOG.log(Level.SEVERE, "Marathon requires authentication");
        return false;
      } else {
        LOG.log(Level.SEVERE, "Failed to kill topology");
        return false;
      }
    } finally {
      // Disconnect to release resources
      conn.disconnect();
    }
  }