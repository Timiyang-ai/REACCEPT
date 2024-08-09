static Set<String> serviceNames(Service<HttpRequest, HttpResponse> service) {
        if (thriftServiceClass == null || entriesMethod == null || interfacesMethod == null) {
            return ImmutableSet.of();
        }
        return service.as(thriftServiceClass)
                      .map(s -> invokeMethod(entriesMethod, s))
                      .map(Map.class::cast)
                      .map(Map::values)
                      .map(ThriftServiceUtils::toServiceName)
                      .orElse(ImmutableSet.of());
    }