public Set<ServiceResponse> createServices(Set<ServiceRequest> requests)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Clusters clusters = getManagementController().getClusters();
    // do all validation checks
    validateCreateRequests(requests, clusters);

    Set<ServiceResponse> createdServices = new HashSet<>();
    for (ServiceRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());
      ServiceGroup sg = cluster.getServiceGroup(request.getServiceGroupName());

      if(StringUtils.isBlank(request.getServiceType())) {
        request.setServiceType(request.getServiceName());
      }

      Service s = cluster.addService(sg, request.getServiceName(), request.getServiceType());

      /*
       * Get the credential_store_supported field only from the stack definition.
       * Not possible to update the value through a request.
       */
      ServiceGroup serviceGroup = cluster.getServiceGroup(s.getServiceGroupId());
      StackId stackId = serviceGroup.getStackId();

      AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
      ServiceInfo serviceInfo = ambariMetaInfo.getService(stackId.getStackName(),
          stackId.getStackVersion(), request.getServiceType());

      boolean credentialStoreSupported = serviceInfo.isCredentialStoreSupported();
      boolean credentialStoreRequired = serviceInfo.isCredentialStoreRequired();

      LOG.info("Service: service_name = {}, service_type = {}, credential_store_supported = {} and credential_store_required = {} from stack definition",
               request.getServiceName(), request.getServiceType(), credentialStoreSupported, credentialStoreRequired);
      /*
       * If request does not have credential_store_enabled field,
       * then get the default from the stack definition.
       */
      if (StringUtils.isNotEmpty(request.getCredentialStoreEnabled())) {
        boolean credentialStoreEnabled = Boolean.parseBoolean(request.getCredentialStoreEnabled());
        boolean enableCredStore = credentialStoreSupported && (credentialStoreRequired || credentialStoreEnabled);
        s.setCredentialStoreEnabled(enableCredStore);
        LOG.info("Service: service_name = {}, service_type = {}, credential_store_enabled = {} from request and resulting" +
                 " credential store enabled status is = {}",
                 request.getServiceName(), request.getServiceType(), credentialStoreEnabled, enableCredStore);
      } else {
        boolean enableCredStore = credentialStoreSupported &&
                                  (credentialStoreRequired || serviceInfo.isCredentialStoreEnabled());
        s.setCredentialStoreEnabled(enableCredStore);
        LOG.info("Service: service_name = {}, service_type = {}, credential_store_enabled = {} from stack definition and resulting" +
                 " credential store enabled status is = {}",
                 s.getName(), s.getServiceType(), serviceInfo.isCredentialStoreEnabled(), enableCredStore);
      }

      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
      createdServices.add(s.convertToResponse());
    }
    return createdServices;
  }