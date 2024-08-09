private static Map<String, Map<String, String>> getInstanceDataByUser(ViewInstanceEntity instanceDefinition) {
    Map<String, Map<String, String>> instanceDataByUser = new HashMap<>();
    for (ViewInstanceDataEntity entity : instanceDefinition.getData()) {

      if (!instanceDataByUser.containsKey(entity.getUser())) {
        instanceDataByUser.put(entity.getUser(), new HashMap<>());
      }
      instanceDataByUser.get(entity.getUser()).put(entity.getName(), entity.getValue());
    }
    return  instanceDataByUser;
  }