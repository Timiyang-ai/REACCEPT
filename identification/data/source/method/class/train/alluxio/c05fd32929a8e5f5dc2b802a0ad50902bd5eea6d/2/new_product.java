public AclActions getPermission(String user, List<String> groups) {
    if (user.equals(mOwningUser)) {
      return new AclActions(getOwningUserActions());
    }
    if (hasExtended()) {
      AclActions actions = mExtendedEntries.getNamedUser(user);
      if (actions != null) {
        AclActions result = new AclActions(actions);
        result.mask(mExtendedEntries.mMaskActions);
        return result;
      }
    }

    boolean isGroupKnown = false;
    AclActions groupActions = new AclActions();
    if (groups.contains(mOwningGroup)) {
      isGroupKnown = true;
      groupActions.merge(getOwningGroupActions());
    }
    if (hasExtended()) {
      for (String group : groups) {
        AclActions actions = mExtendedEntries.getNamedGroup(group);
        if (actions != null) {
          isGroupKnown = true;
          groupActions.merge(actions);
        }
      }
    }
    if (isGroupKnown) {
      if (hasExtended()) {
        groupActions.mask(mExtendedEntries.mMaskActions);
      }
      return groupActions;
    }

    return getOtherActions();
  }