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

      if(StringUtils.isBlank(request.getServiceDisplayName())) {
        request.setServiceDisplayName(request.getServiceName());
      }

      RepositoryVersionEntity repositoryVersion = request.getResolvedRepository();

      if (null == repositoryVersion) {
        throw new AmbariException("Could not find any repository on the request.");
      }

      Service s = cluster.addService(sg, request.getServiceName(), request.getServiceDisplayName(), repositoryVersion);
      if (repositoryVersion.getType() != RepositoryType.STANDARD
          && cluster.getProvisioningState() == State.INIT) {
        throw new AmbariException(String.format(
            "Unable to add %s to %s because the cluster is still being provisioned and the repository for the service is not %s: $s",
            request.getServiceName(), cluster.getClusterName(), RepositoryType.STANDARD,
            repositoryVersion));
      }
      /*
       * Get the credential_store_supported field only from the stack definition.
       * Not possible to update the value through a request.
       */
      StackId stackId = repositoryVersion.getStackId();
      AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();
      ServiceInfo serviceInfo = ambariMetaInfo.getService(stackId.getStackName(),
          stackId.getStackVersion(), request.getServiceDisplayName());

      boolean credentialStoreSupported = serviceInfo.isCredentialStoreSupported();
      boolean credentialStoreRequired = serviceInfo.isCredentialStoreRequired();

      LOG.info("Service: {}, credential_store_supported = {} and credential_store_required = {} from stack definition",
               request.getServiceDisplayName(), credentialStoreSupported, credentialStoreRequired);
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
                 request.getServiceDisplayName(), credentialStoreEnabled, enableCredStore);
      } else {
        boolean enableCredStore = credentialStoreSupported &&
                                  (credentialStoreRequired || serviceInfo.isCredentialStoreEnabled());
        s.setCredentialStoreEnabled(enableCredStore);
        LOG.info("Service: {}, credential_store_enabled = {} from stack definition and resulting" +
                 " credential store enabled status is = {}",
                 s.getServiceDisplayName(), serviceInfo.isCredentialStoreEnabled(), enableCredStore);
      }

      // Initialize service widgets
      getManagementController().initializeWidgetsAndLayouts(cluster, s);
      createdServices.add(s.convertToResponse());
    }
    return createdServices;
  }