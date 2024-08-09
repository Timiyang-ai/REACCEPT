private List<Map<String, String>> processHostGroupComponents(ExportedHostGroup group) {
    List<Map<String, String>> listHostGroupComponents = new ArrayList<>();
    for (String component : group.getComponents()) {
      Map<String, String> mapComponentProperties = new HashMap<>();
      listHostGroupComponents.add(mapComponentProperties);
      mapComponentProperties.put("name", component);
    }
    return listHostGroupComponents;
  }