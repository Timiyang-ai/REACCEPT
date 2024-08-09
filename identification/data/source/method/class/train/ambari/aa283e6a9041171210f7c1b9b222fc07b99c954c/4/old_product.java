public LdapBatchDto synchronizeExistingLdapGroups(LdapBatchDto batchInfo) throws AmbariException {
    final Map<String, Group> internalGroupsMap = getInternalGroups();
    final Map<String, User> internalUsersMap = getInternalUsers();

    final Set<Group> internalGroupSet = Sets.newHashSet(internalGroupsMap.values());

    for (Group group : internalGroupSet) {
      if (group.isLdapGroup()) {
        Set<LdapGroupDto> groupDtos = getLdapGroups(group.getGroupName());
        if (groupDtos.isEmpty()) {
          batchInfo.getGroupsToBeRemoved().add(group.getGroupName());
        } else {
          LdapGroupDto groupDto = groupDtos.iterator().next();
          refreshGroupMembers(batchInfo, groupDto, internalUsersMap, internalGroupsMap, null);
        }
      }
    }

    return batchInfo;
  }