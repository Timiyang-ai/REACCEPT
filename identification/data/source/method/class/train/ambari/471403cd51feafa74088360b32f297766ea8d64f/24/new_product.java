protected Set<ServiceResponse> getServices(Set<ServiceRequest> requests)
      throws AmbariException {
    Set<ServiceResponse> response = new HashSet<>();
    for (ServiceRequest request : requests) {
      try {
        response.addAll(getServices(request));
      } catch (ServiceNotFoundException e) {
        if (requests.size() == 1) {
          // only throw exception if 1 request.
          // there will be > 1 request in case of OR predicate
          throw e;
        }
      }
    }
    return response;
  }