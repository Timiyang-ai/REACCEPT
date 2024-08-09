public boolean manageTopology() {
    String topologyName = Context.topologyName(config);
    // 1. Do prepare work
    // create an instance of state manager
    String statemgrClass = Context.stateManagerClass(config);
    IStateManager statemgr;
    try {
      statemgr = ReflectionUtils.newInstance(statemgrClass);
    } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
      LOG.log(Level.SEVERE, "Failed to instantiate instances", e);
      return false;
    }

    boolean isSuccessful = false;

    // Put it in a try block so that we can always clean resources
    try {
      // initialize the statemgr
      statemgr.initialize(config);

      // TODO(mfu): timeout should read from config
      SchedulerStateManagerAdaptor adaptor = new SchedulerStateManagerAdaptor(statemgr, 5000);

      boolean isValid = validateRuntimeManage(adaptor, topologyName);

      // 2. Try to manage topology if valid
      if (isValid) {
        // invoke the appropriate command to manage the topology
        LOG.log(Level.FINE, "Topology: {0} to be {1}ed", new Object[]{topologyName, command});

        // build the runtime config
        Config runtime = Config.newBuilder()
            .put(Keys.topologyName(), Context.topologyName(config))
            .put(Keys.schedulerStateManagerAdaptor(), adaptor)
            .build();

        // Create a ISchedulerClient basing on the config
        ISchedulerClient schedulerClient = getSchedulerClient(runtime);
        if (schedulerClient == null) {
          LOG.severe("Failed to initialize scheduler client");
          return false;
        }

        isSuccessful = callRuntimeManagerRunner(runtime, schedulerClient);
      }
    } finally {
      // 3. Do post work basing on the result
      // Currently nothing to do here

      // 4. Close the resources
      SysUtils.closeIgnoringExceptions(statemgr);
    }

    return isSuccessful;
  }