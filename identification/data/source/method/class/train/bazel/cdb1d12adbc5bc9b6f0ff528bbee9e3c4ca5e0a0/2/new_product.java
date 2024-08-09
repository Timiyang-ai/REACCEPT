@Override
  public synchronized Set<SkyKey> setValue(SkyValue value, Version version)
      throws InterruptedException {
    Preconditions.checkState(isReady(), "%s %s", this, value);
    assertVersionCompatibleWhenSettingValue(version, value);
    this.lastEvaluatedVersion = version;

    if (!isEligibleForChangePruning()) {
      this.lastChangedVersion = version;
      this.value = value;
    } else if (isDirty() && getDirtyBuildingState().unchangedFromLastBuild(value)) {
      // If the value is the same as before, just use the old value. Note that we don't use the new
      // value, because preserving == equality is even better than .equals() equality.
      this.value = getDirtyBuildingState().getLastBuildValue();
    } else {
      boolean forcedRebuild =
          isDirty() && getDirtyBuildingState().getDirtyState() == DirtyState.FORCED_REBUILDING;
      if (!forcedRebuild && this.lastChangedVersion.equals(version)) {
        logError(
            new ChangedValueAtSameVersionException(this.lastChangedVersion, version, value, this));
      }
      // If this is a new value, or it has changed since the last build, set the version to the
      // current graph version.
      this.lastChangedVersion = version;
      this.value = value;
    }
    return setStateFinishedAndReturnReverseDepsToSignal();
  }