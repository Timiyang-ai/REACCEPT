public LdapBatchDto synchronizeExistingLdapGroups(LdapBatchDto batchInfo, boolean collectIgnoredUsers) throws AmbariException {
    LOG.trace("Synchronize Existing LDAP groups...");
    final Map<String, Group> internalGroupsMap = getInternalGroups();
    final Map<String, User> internalUsersMap = getInternalUsers();

    final Set<Group> internalGroupSet = Sets.newHashSet(internalGroupsMap.values());

    for (Group group : internalGroupSet) {
      if (group.isLdapGroup()) {
        Set<LdapGroupDto> groupDtos = getLdapGroups(group.getGroupName());
        if (groupDtos.isEmpty()) {
          LdapGroupDto groupDto = new LdapGroupDto();
          groupDto.setGroupName(group.getGroupName());
          batchInfo.getGroupsToBeRemoved().add(groupDto);
        } else {
          LdapGroupDto groupDto = groupDtos.iterator().next();
          refreshGroupMembers(batchInfo, groupDto, internalUsersMap, internalGroupsMap, null, true, collectIgnoredUsers);
        }
      }
    }

    return batchInfo;
  }