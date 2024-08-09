static Set<String> serviceNames(Service<HttpRequest, HttpResponse> service) {
        if (thriftServiceClass == null || entriesMethod == null || interfacesMethod == null) {
            return ImmutableSet.of();
        }
        return service.as(thriftServiceClass)
                      .map(s -> {
                          @SuppressWarnings("unchecked")
                          final Map<String, ?> entries = (Map<String, ?>) invokeMethod(entriesMethod, s);
                          assert entries != null;
                          return toServiceName(entries.values());
                      })
                      .orElse(ImmutableSet.of());
    }