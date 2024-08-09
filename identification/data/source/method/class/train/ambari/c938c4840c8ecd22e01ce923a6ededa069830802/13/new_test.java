  public static void createHosts(AmbariManagementController controller, Set<HostRequest> requests)
      throws AmbariException, AuthorizationException, NoSuchFieldException, IllegalAccessException {
    HostResourceProvider provider = getHostProvider(controller);
    Set<Map<String, Object>> properties = new HashSet<>();

    for (HostRequest request : requests) {
      Map<String, Object> requestProperties = new HashMap<>();
      requestProperties.put(HostResourceProvider.HOST_HOST_NAME_PROPERTY_ID, request.getHostname());
      requestProperties.put(HostResourceProvider.HOST_CLUSTER_NAME_PROPERTY_ID, request.getClusterName());
      if (null != request.getRackInfo()) {
        requestProperties.put(HostResourceProvider.HOST_RACK_INFO_PROPERTY_ID, UUID.randomUUID().toString());
      }
      properties.add(requestProperties);
    }

    provider.createHosts(PropertyHelper.getCreateRequest(properties, Collections.emptyMap()));
  }