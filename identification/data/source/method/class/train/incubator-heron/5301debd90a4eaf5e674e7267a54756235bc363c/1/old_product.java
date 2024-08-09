protected boolean submitTopology(String[] appConfs) {

    if (!this.topologyName.equals(this.topologyName.toLowerCase())) {
      throw new TopologySubmissionException("K8S scheduler does not allow upper case topologies.");
    }

    for (String appConf : appConfs) {
      try {
        deployContainer(appConf);
      } catch (IOException ioe) {
        LOG.log(Level.SEVERE, "Problem deploying container with config: " + appConf);
        return false;
      }
    }
    return true;
  }