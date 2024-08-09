public void createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return;
    }
    Clusters clusters = getManagementController().getClusters();
    // do all validation checks
    validateCreateRequests(requests, clusters);

    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());

      // Already checked that service does not exist
      Service s = cluster.addService(request.getServiceName());

      /**
       * Get the credential_store_supported field only from the stack definition.
       * Not possible to update the value through a request.
       */
      StackId stackId = cluster.getDesiredStackVersion();
      AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
      ServiceInfo serviceInfo = ambariMetaInfo.getService(stackId.getStackName(),
          stackId.getStackVersion(), request.getServiceName());
      s.setCredentialStoreSupported(serviceInfo.isCredentialStoreSupported());
      LOG.info("Service: {}, credential_store_supported from stack definition:{}", request.getServiceName(),
          serviceInfo.isCredentialStoreSupported());

      /**
       * If request does not have credential_store_enabled field,
       * then get the default from the stack definition.
       */
      if (StringUtils.isNotEmpty(request.getCredentialStoreEnabled())) {
        boolean credentialStoreEnabled = Boolean.parseBoolean(request.getCredentialStoreEnabled());
        s.setCredentialStoreEnabled(credentialStoreEnabled);
        LOG.info("Service: {}, credential_store_enabled from request: {}", request.getServiceName(),
            credentialStoreEnabled);
      } else {
        s.setCredentialStoreEnabled(serviceInfo.isCredentialStoreEnabled());
        LOG.info("Service: {}, credential_store_enabled from stack definition:{}", s.getName(),
            serviceInfo.isCredentialStoreEnabled());
      }

      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
    }
  }