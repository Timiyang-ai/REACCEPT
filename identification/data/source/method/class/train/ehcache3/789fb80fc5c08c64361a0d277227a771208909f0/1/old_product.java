public static SharedClusteredResourcePool shared(String sharedResource) {
    return new SharedClusteredResourcePoolImpl(sharedResource);
  }