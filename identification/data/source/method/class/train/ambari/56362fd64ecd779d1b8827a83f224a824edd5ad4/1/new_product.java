public Map<String, Collection<String>> getComponents() {
    Map<String, Collection<String>> serviceComponents = new HashMap<>();
    for (String service : getServices()) {
      Collection<String> components = new HashSet<>();
      components.addAll(getComponents(service));
      serviceComponents.put(service, components);
    }
    return serviceComponents;
  }