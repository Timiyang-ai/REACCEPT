public LdapBatchDto synchronizeExistingLdapGroups(LdapBatchDto batchInfo) throws AmbariException {
    final Map<String, Group> internalGroupsMap = getInternalGroups();
    final Map<String, User> internalUsersMap = getInternalUsers();

    for (Group group : internalGroupsMap.values()) {
      if (group.isLdapGroup()) {
        Set<LdapGroupDto> groupDtos = getLdapGroups(group.getGroupName());
        if (groupDtos.isEmpty()) {
          batchInfo.getGroupsToBeRemoved().add(group.getGroupName());
        } else {
          LdapGroupDto groupDto = groupDtos.iterator().next();
          refreshGroupMembers(batchInfo, groupDto, internalUsersMap, null);
        }
      }
    }

    return batchInfo;
  }