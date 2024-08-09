public Set<DistributedMember> findMembers(String... groups) {
    if (groups == null || groups.length == 0) {
      throw new IllegalArgumentException("groups cannot be empty");
    }

    Set<DistributedMember> allMembers = getAllServers();

    // if groups contains "cluster" group, return all members
    if (Arrays.stream(groups).filter(g -> g.equals("cluster")).findFirst().isPresent()) {
      return allMembers;
    }

    Set<DistributedMember> matchingMembers = new HashSet<>();
    for (String group : groups) {
      matchingMembers.addAll(
          allMembers.stream().filter(m -> m.getGroups() != null && m.getGroups().contains(group))
              .collect(Collectors.toSet()));
    }
    return matchingMembers;
  }