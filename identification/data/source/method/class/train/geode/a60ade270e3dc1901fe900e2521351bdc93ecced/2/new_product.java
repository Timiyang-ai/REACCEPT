public Set<DistributedMember> findMembers(boolean includeLocators, String... groups) {
    if (groups == null || groups.length == 0) {
      groups = new String[] {CacheElement.CLUSTER};
    }

    Set<DistributedMember> all = includeLocators ? getAllServersAndLocators() : getAllServers();

    // if groups contains "cluster" group, return all members
    if (Arrays.asList(groups).contains(CacheElement.CLUSTER)) {
      return all;
    }

    Set<DistributedMember> matchingMembers = new HashSet<>();
    for (String group : groups) {
      matchingMembers.addAll(
          all.stream().filter(m -> m.getGroups() != null && m.getGroups().contains(group))
              .collect(Collectors.toSet()));
    }
    return matchingMembers;
  }