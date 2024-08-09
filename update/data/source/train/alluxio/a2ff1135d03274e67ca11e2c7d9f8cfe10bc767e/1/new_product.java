public static List<String> getGroups(String userName) throws IOException {
    GroupMappingService groupMappingService = GroupMappingService.Factory.get();
    return groupMappingService.getGroups(userName);
  }