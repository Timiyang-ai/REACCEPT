public AclActions getPermission(String user, List<String> groups) {
    if (user.equals(mOwningUser)) {
      return new AclActions(getOwningUserActions());
    }
    if (mUserActions.containsKey(user)) {
      return new AclActions(mUserActions.get(user));
    }

    boolean isGroupKnown = false;
    AclActions groupActions = new AclActions();
    if (groups.contains(mOwningGroup)) {
      isGroupKnown = true;
      groupActions.merge(getOwningGroupActions());
    }
    for (String group : groups) {
      if (mGroupActions.containsKey(group)) {
        isGroupKnown = true;
        groupActions.merge(mGroupActions.get(group));
      }
    }
    if (isGroupKnown) {
      return groupActions;
    }

    return new AclActions(mOtherActions);
  }