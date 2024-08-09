public static List<String> getGroups(String userName, AlluxioConfiguration conf)
      throws IOException {
    GroupMappingService groupMappingService = GroupMappingService.Factory.get(conf);
    return groupMappingService.getGroups(userName);
  }