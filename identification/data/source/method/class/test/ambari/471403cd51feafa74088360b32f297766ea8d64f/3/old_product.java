public void createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }

    Clusters clusters = getManagementController().getClusters();
    // do all validation checks
    validateCreateRequests(requests, clusters);

    Set<Cluster> clustersSetFromRequests = new HashSet<>();

    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());

      RepositoryVersionEntity repositoryVersion = request.getResolvedRepository();

      if (null == repositoryVersion) {
        throw new AmbariException("Could not find any repository on the request.");
      }

      if (repositoryVersion.getType() != RepositoryType.STANDARD
          && cluster.getProvisioningState() == State.INIT) {
        throw new AmbariException(String.format(
            "Unable to add %s to %s because the cluster is still being provisioned and the repository for the service is not %s: $s",
            request.getServiceName(), cluster.getClusterName(), RepositoryType.STANDARD,
            repositoryVersion));
      }

      Service s = cluster.addService(request.getServiceName(), repositoryVersion);

      /*
       * Get the credential_store_supported field only from the stack definition.
       * Not possible to update the value through a request.
       */
      StackId stackId = repositoryVersion.getStackId();
      AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
      ServiceInfo serviceInfo = ambariMetaInfo.getService(stackId.getStackName(),
          stackId.getStackVersion(), request.getServiceName());

      boolean credentialStoreSupported = serviceInfo.isCredentialStoreSupported();
      boolean credentialStoreRequired = serviceInfo.isCredentialStoreRequired();

      LOG.info("Service: {}, credential_store_supported = {} and credential_store_required = {} from stack definition",
               request.getServiceName(), credentialStoreSupported, credentialStoreRequired);
      /*
       * If request does not have credential_store_enabled field,
       * then get the default from the stack definition.
       */
      if (StringUtils.isNotEmpty(request.getCredentialStoreEnabled())) {
        boolean credentialStoreEnabled = Boolean.parseBoolean(request.getCredentialStoreEnabled());
        boolean enableCredStore = credentialStoreSupported && (credentialStoreRequired || credentialStoreEnabled);
        s.setCredentialStoreEnabled(enableCredStore);
        LOG.info("Service: {}, credential_store_enabled = {} from request and resulting" +
                 " credential store enabled status is = {}",
                 request.getServiceName(), credentialStoreEnabled, enableCredStore);
      } else {
        boolean enableCredStore = credentialStoreSupported &&
                                  (credentialStoreRequired || serviceInfo.isCredentialStoreEnabled());
        s.setCredentialStoreEnabled(enableCredStore);
        LOG.info("Service: {}, credential_store_enabled = {} from stack definition and resulting" +
                 " credential store enabled status is = {}",
                 s.getName(), serviceInfo.isCredentialStoreEnabled(), enableCredStore);
      }

      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
      clustersSetFromRequests.add(cluster);
    }

    // Create cluster widgets and layouts
    for (Cluster cluster : clustersSetFromRequests) {
      getManagementController().initializeWidgetsAndLayouts(cluster, null);
    }
  }