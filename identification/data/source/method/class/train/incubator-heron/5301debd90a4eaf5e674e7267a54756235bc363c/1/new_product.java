protected boolean submitTopology(String[] appConfs) {
    if (!this.topologyName.equals(this.topologyName.toLowerCase())) {
      throw new TopologySubmissionException("K8S scheduler does not allow upper case topologies.");
    }

    for (String appConf : appConfs) {
      try {
        deployContainer(appConf);
      } catch (IOException ioe) {
        final String message = ioe.getMessage();
        LOG.log(Level.SEVERE, "Problem deploying container: " + ioe.getMessage(), ioe);
        LOG.log(Level.SEVERE, "Container config: " + appConf);
        throw new TopologySubmissionException(message);
      }
    }
    return true;
  }