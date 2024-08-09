public boolean init(String[] args) throws ParseException, IOException, Exception
  {

    Options opts = new Options();
    opts.addOption("app_attempt_id", true, "App Attempt ID. Not to be used unless for testing purposes");
    opts.addOption("priority", true, "Application Priority. Default 0");

    opts.addOption("help", false, "Print usage");
    CommandLine cliParser = new GnuParser().parse(opts, args);

    // option "help" overrides and cancels any run
    if (cliParser.hasOption("help")) {
      printUsage(opts);
      return false;
    }

    Map<String, String> envs = System.getenv();

    appAttemptID = Records.newRecord(ApplicationAttemptId.class);
    if (!envs.containsKey(ApplicationConstants.AM_CONTAINER_ID_ENV)) {
      if (cliParser.hasOption("app_attempt_id")) {
        String appIdStr = cliParser.getOptionValue("app_attempt_id", "");
        appAttemptID = ConverterUtils.toApplicationAttemptId(appIdStr);
      }
      else {
        throw new IllegalArgumentException("Application Attempt Id not set in the environment");
      }
    }
    else {
      ContainerId containerId = ConverterUtils.toContainerId(envs.get(ApplicationConstants.AM_CONTAINER_ID_ENV));
      appAttemptID = containerId.getApplicationAttemptId();
    }

    LOG.info("Application master for app"
             + ", appId=" + appAttemptID.getApplicationId().getId()
             + ", clustertimestamp=" + appAttemptID.getApplicationId().getClusterTimestamp()
             + ", attemptId=" + appAttemptID.getAttemptId());

    requestPriority = Integer.parseInt(cliParser.getOptionValue("priority", "0"));

    // set topology - read from localized dfs location populated by submit client
    //TopologyBuilder b = new TopologyBuilder(conf);
    //Properties tplgProperties = readProperties("./stram.properties");
    //b.addFromProperties(tplgProperties);
    //this.topology = b.getTopology();

    FileInputStream fis = new FileInputStream("./" + DAG.SER_FILE_NAME);
    this.topology = DAG.read(fis);
    fis.close();
    // "debug" simply dumps all data using LOG.info
    if (topology.isDebug()) {
      dumpOutDebugInfo();
    }

    this.dnmgr = new StreamingContainerManager(topology);

    // start RPC server
    rpcImpl = new StreamingContainerParent(this.getClass().getName(), dnmgr);
    rpcImpl.init(conf);
    rpcImpl.start();
    LOG.info("Container callback server listening at " + rpcImpl.getAddress());

    LOG.info("Initializing logical topology with {} operators in {} containers", topology.getAllOperators().size(), dnmgr.getNumRequiredContainers());

    // start buffer server
    com.malhartech.bufferserver.Server s = new Server(0);
    SocketAddress bindAddr = s.run();
    this.bufferServerAddress = ((InetSocketAddress) bindAddr);
    LOG.info("Buffer server started: {}", bufferServerAddress);

    StramAppContext appContext = new ClusterAppContextImpl();
    // start web service
    try {
      WebApp webApp = WebApps.$for("stram", StramAppContext.class, appContext, "ws").with(conf).
        start(new StramWebApp(this.dnmgr));
      LOG.info("Started web service at port: " + webApp.port());
      this.appMasterTrackingUrl = NetUtils.getConnectAddress(rpcImpl.getAddress()).getHostName() + ":" + webApp.port();
      LOG.info("Setting tracking URL to: " + appMasterTrackingUrl);
    }
    catch (Exception e) {
      LOG.error("Webapps failed to start. Ignoring for now:", e);
    }

    return true;
  }