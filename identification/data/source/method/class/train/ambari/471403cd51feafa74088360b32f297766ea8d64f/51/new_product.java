protected synchronized RequestStatusResponse updateServices(
      Set<ServiceRequest> requests, Map<String, String> requestProperties,
      boolean runSmokeTest, boolean reconfigureClients) throws AmbariException {

    AmbariManagementController controller = getManagementController();

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Map<State, List<Service>> changedServices
        = new HashMap<State, List<Service>>();
    Map<State, List<ServiceComponent>> changedComps =
        new HashMap<State, List<ServiceComponent>>();
    Map<String, Map<State, List<ServiceComponentHost>>> changedScHosts =
        new HashMap<String, Map<State, List<ServiceComponentHost>>>();
    Collection<ServiceComponentHost> ignoredScHosts =
        new ArrayList<ServiceComponentHost>();

    Set<String> clusterNames = new HashSet<String>();
    Map<String, Set<String>> serviceNames = new HashMap<String, Set<String>>();
    Set<State> seenNewStates = new HashSet<State>();


    Clusters       clusters        = controller.getClusters();
    AmbariMetaInfo ambariMetaInfo   = controller.getAmbariMetaInfo();
    Set<String> maintenanceClusters = new HashSet<String>();

    for (ServiceRequest request : requests) {
      if (request.getClusterName() == null
          || request.getClusterName().isEmpty()
          || request.getServiceName() == null
          || request.getServiceName().isEmpty()) {
        throw new IllegalArgumentException("Invalid arguments, cluster name"
            + " and service name should be provided to update services");
      }

      LOG.info("Received a updateService request"
          + ", clusterName=" + request.getClusterName()
          + ", serviceName=" + request.getServiceName()
          + ", request=" + request.toString());

      clusterNames.add(request.getClusterName());

      if (clusterNames.size() > 1) {
        throw new IllegalArgumentException("Updates to multiple clusters is not"
            + " supported");
      }

      if (!serviceNames.containsKey(request.getClusterName())) {
        serviceNames.put(request.getClusterName(), new HashSet<String>());
      }
      if (serviceNames.get(request.getClusterName())
          .contains(request.getServiceName())) {
        // TODO throw single exception
        throw new IllegalArgumentException("Invalid request contains duplicate"
            + " service names");
      }
      serviceNames.get(request.getClusterName()).add(request.getServiceName());

      Cluster cluster = clusters.getCluster(request.getClusterName());
      Service s = cluster.getService(request.getServiceName());
      State oldState = s.getDesiredState();
      State newState = null;
      if (request.getDesiredState() != null) {
        newState = State.valueOf(request.getDesiredState());
        if (!newState.isValidDesiredState()) {
          throw new IllegalArgumentException("Invalid arguments, invalid"
              + " desired state, desiredState=" + newState);
        }
      }
      
      if (null != request.getMaintenanceState()) {
        MaintenanceState newMaint = MaintenanceState.valueOf(request.getMaintenanceState());
        if (newMaint  != s.getMaintenanceState()) {
          if (newMaint.equals(MaintenanceState.IMPLIED)) {
            throw new IllegalArgumentException("Invalid arguments, can only set " +
              "maintenance state to one of " + EnumSet.of(MaintenanceState.OFF, MaintenanceState.ON));
          } else {
            s.setMaintenanceState(newMaint);
            
            maintenanceClusters.add(cluster.getClusterName());
          }
        }
      }

      if (newState == null) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("Nothing to do for new updateService request"
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + request.getServiceName()
              + ", newDesiredState=null");
        }
        continue;
      }
      
      if (requests.size() > 1 && MaintenanceState.OFF != s.getMaintenanceState()) {
        LOG.info("Operations cannot be applied to service " + s.getName() +
            " in the maintenance state of " + s.getMaintenanceState());
        continue;
      }
      

      seenNewStates.add(newState);

      if (newState != oldState) {
        if (!State.isValidDesiredStateTransition(oldState, newState)) {
          throw new AmbariException("Invalid transition for"
              + " service"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceName=" + s.getName()
              + ", currentDesiredState=" + oldState
              + ", newDesiredState=" + newState);

        }
        if (!changedServices.containsKey(newState)) {
          changedServices.put(newState, new ArrayList<Service>());
        }
        changedServices.get(newState).add(s);
      }

      // TODO should we check whether all servicecomponents and
      // servicecomponenthosts are in the required desired state?

      for (ServiceComponent sc : s.getServiceComponents().values()) {
        State oldScState = sc.getDesiredState();
        if (newState != oldScState) {
          if (sc.isClientComponent() &&
              !newState.isValidClientComponentState()) {
            continue;
          }
          if (!State.isValidDesiredStateTransition(oldScState, newState)) {
            throw new AmbariException("Invalid transition for"
                + " servicecomponent"
                + ", clusterName=" + cluster.getClusterName()
                + ", clusterId=" + cluster.getClusterId()
                + ", serviceName=" + sc.getServiceName()
                + ", componentName=" + sc.getName()
                + ", currentDesiredState=" + oldScState
                + ", newDesiredState=" + newState);
          }
          if (!changedComps.containsKey(newState)) {
            changedComps.put(newState, new ArrayList<ServiceComponent>());
          }
          changedComps.get(newState).add(sc);
        }
        if (LOG.isDebugEnabled()) {
          LOG.debug("Handling update to ServiceComponent"
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + s.getName()
              + ", componentName=" + sc.getName()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);
        }

        for (ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
          State oldSchState = sch.getState();
          if (oldSchState == State.DISABLED || oldSchState == State.UNKNOWN) {
            //Ignore host components updates in this state
            if (LOG.isDebugEnabled()) {
              LOG.debug("Ignoring ServiceComponentHost"
                  + ", clusterName=" + request.getClusterName()
                  + ", serviceName=" + s.getName()
                  + ", componentName=" + sc.getName()
                  + ", hostname=" + sch.getHostName()
                  + ", currentState=" + oldSchState
                  + ", newDesiredState=" + newState);
            }
            continue;
          }
          
          if (newState == oldSchState) {
            ignoredScHosts.add(sch);
            if (LOG.isDebugEnabled()) {
              LOG.debug("Ignoring ServiceComponentHost"
                  + ", clusterName=" + request.getClusterName()
                  + ", serviceName=" + s.getName()
                  + ", componentName=" + sc.getName()
                  + ", hostname=" + sch.getHostName()
                  + ", currentState=" + oldSchState
                  + ", newDesiredState=" + newState);
            }
            continue;
          }
          
          MaintenanceState schMaint = controller.getEffectiveMaintenanceState(sch);
          if (MaintenanceState.ON == schMaint ||
              (requests.size() > 1 && MaintenanceState.OFF != schMaint)) {
            ignoredScHosts.add(sch);
            if (LOG.isDebugEnabled()) {
              LOG.debug("Ignoring " + schMaint + " ServiceComponentHost"
                  + ", clusterName=" + request.getClusterName()
                  + ", serviceName=" + s.getName()
                  + ", componentName=" + sc.getName()
                  + ", hostname=" + sch.getHostName());
            }
            continue;
          }
          Host host = clusters.getHost(sch.getHostName());

          if (schMaint == MaintenanceState.IMPLIED
             && host != null
             && host.getMaintenanceState(cluster.getClusterId()) != MaintenanceState.OFF) {

            // Host is in Passive mode, ignore the SCH
            ignoredScHosts.add(sch);
            LOG.info("Ignoring ServiceComponentHost since "
              + "the host is in passive mode"
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + s.getName()
              + ", componentName=" + sc.getName()
              + ", hostname=" + sch.getHostName());
            continue;
          }
          
          
          if (sc.isClientComponent() &&
              !newState.isValidClientComponentState()) {
            continue;
          }
          /**
           * This is hack for now wherein we don't fail if the
           * sch is in INSTALL_FAILED
           */
          if (!State.isValidStateTransition(oldSchState, newState)) {
            String error = "Invalid transition for"
                + " servicecomponenthost"
                + ", clusterName=" + cluster.getClusterName()
                + ", clusterId=" + cluster.getClusterId()
                + ", serviceName=" + sch.getServiceName()
                + ", componentName=" + sch.getServiceComponentName()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState;
            StackId sid = cluster.getDesiredStackVersion();

            if ( ambariMetaInfo.getComponentCategory(
                sid.getStackName(), sid.getStackVersion(), sc.getServiceName(),
                sch.getServiceComponentName()).isMaster()) {
              throw new AmbariException(error);
            } else {
              LOG.warn("Ignoring: " + error);
              continue;
            }
          }
          if (!changedScHosts.containsKey(sc.getName())) {
            changedScHosts.put(sc.getName(),
                new HashMap<State, List<ServiceComponentHost>>());
          }
          if (!changedScHosts.get(sc.getName()).containsKey(newState)) {
            changedScHosts.get(sc.getName()).put(newState,
                new ArrayList<ServiceComponentHost>());
          }
          if (LOG.isDebugEnabled()) {
            LOG.debug("Handling update to ServiceComponentHost"
                + ", clusterName=" + request.getClusterName()
                + ", serviceName=" + s.getName()
                + ", componentName=" + sc.getName()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
          }
          changedScHosts.get(sc.getName()).get(newState).add(sch);
        }
      }
    }

    if (seenNewStates.size() > 1) {
      // TODO should we handle this scenario
      throw new IllegalArgumentException("Cannot handle different desired state"
          + " changes for a set of services at the same time");
    }
    
    if (maintenanceClusters.size() > 0) {
      try {
        MaintenanceStateHelper.createRequests(controller, requestProperties,
            maintenanceClusters);
      } catch (Exception e) {
        LOG.warn("Could not send maintenance state to Nagios (" + e.getMessage() + ")");
      }
    }

    Cluster cluster = clusters.getCluster(clusterNames.iterator().next());

    return controller.createStages(cluster, requestProperties, null, changedServices, changedComps, changedScHosts,
        ignoredScHosts, runSmokeTest, reconfigureClients);
  }