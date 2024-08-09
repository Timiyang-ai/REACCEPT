public static String getPropertyNameForBranch(WorkUnitState workUnitState, String key) {
    Preconditions.checkNotNull(workUnitState, "Cannot get a property from a null WorkUnit");
    Preconditions.checkNotNull(key, "Cannot get a the value for a null key");

    if (!workUnitState.contains(ConfigurationKeys.FORK_BRANCH_ID_KEY)) {
      return key;
    } else {
      return workUnitState.getPropAsInt(ConfigurationKeys.FORK_BRANCH_ID_KEY) == -1 ? key : key + "."
          + workUnitState.getPropAsInt(ConfigurationKeys.FORK_BRANCH_ID_KEY);
    }
  }