protected Set<ServiceComponentResponse> getComponents(Set<ServiceComponentRequest> requests) throws AmbariException {
    Set<ServiceComponentResponse> response = new HashSet<>();
    for (ServiceComponentRequest request : requests) {
      try {
        response.addAll(getComponents(request));
      } catch (ObjectNotFoundException e) {
        if (requests.size() == 1) {
          // only throw exception if 1 request.
          // there will be > 1 request in case of OR predicate
          throw e;
        }
      }
    }
    return response;
  }