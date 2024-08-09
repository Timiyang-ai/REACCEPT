public Map<String, Collection<String>> getComponents() {
    Map<String, Collection<String>> serviceComponents = new HashMap<String, Collection<String>>();
    for (String service : getServices()) {
      Collection<String> components = new HashSet<String>();
      components.addAll(getComponents(service));
      serviceComponents.put(service, components);
    }
    return serviceComponents;
  }