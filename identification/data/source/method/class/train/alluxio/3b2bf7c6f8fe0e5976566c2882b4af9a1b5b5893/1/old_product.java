public void start() throws IOException, YarnException {
    // create a client to talk to NodeManager
    mNMClient = NMClient.createNMClient();
    mNMClient.init(mYarnConf);
    mNMClient.start();

    // Create a client to talk to the ResourceManager
    mRMClient = AMRMClientAsync.createAMRMClientAsync(100, this);
    mRMClient.init(mYarnConf);
    mRMClient.start();

    // Create a client to talk to Yarn e.g. to find out what nodes exist in the cluster
    mYarnClient = YarnClient.createYarnClient();
    mYarnClient.init(mYarnConf);
    mYarnClient.start();

    // Register with ResourceManager
    String hostname = NetworkAddressUtils.getLocalHostName(
        Configuration.createServerConf());
    mRMClient.registerApplicationMaster(hostname, 0 /* port */, "" /* tracking url */);
    LOG.info("ApplicationMaster registered");
  }