public void updateStateSync(StateUpdate stateUpdate, String attribution) {
    checkIfNoStateUpdatesMethod();

    if (mComponentTree == null) {
      return;
    }

    mComponentTree.updateStateSync(
        mComponentScope.getGlobalKey(), stateUpdate, attribution, isCreateLayoutInProgress());
  }