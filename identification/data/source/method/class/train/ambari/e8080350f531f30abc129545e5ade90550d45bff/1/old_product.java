private List<Map<String, String>> processHostGroupComponents(ExportedHostGroup group) {
    List<Map<String, String>> listHostGroupComponents = new ArrayList<Map<String, String>>();
    for (String component : group.getComponents()) {
      Map<String, String> mapComponentProperties = new HashMap<String, String>();
      listHostGroupComponents.add(mapComponentProperties);
      mapComponentProperties.put("name", component);
    }
    return listHostGroupComponents;
  }