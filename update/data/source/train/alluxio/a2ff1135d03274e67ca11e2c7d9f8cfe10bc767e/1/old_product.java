public static List<String> getGroups(String userName) throws IOException {
    CachedGroupMapping cachedGroupMapping = GroupMappingService.Factory.getCachedGroupMapping();
    return cachedGroupMapping.getGroups(userName);
  }