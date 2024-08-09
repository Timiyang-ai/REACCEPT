synchronized EvaluationResult<K> evaluate(
      Map<K, T> instancesNeedingUpdate,
      InstanceStateProvider<K, T> stateProvider) {

    if (stateMachine.getState() == OneWayStatus.IDLE) {
      stateMachine.transition(OneWayStatus.WORKING);
    }
    Preconditions.checkState(
        stateMachine.getState() == OneWayStatus.WORKING,
        "Attempted to evaluate an inactive job updater.");

    // Call order is important here: update on-demand instances, evaluate new instances, compute
    // job update state.
    ImmutableMap.Builder<K, SideEffect> actions = ImmutableMap.<K, SideEffect>builder()
        // Re-evaluate instances that are in need of update.
        .putAll(evaluateInstances(instancesNeedingUpdate));

    if (computeJobUpdateStatus() == OneWayStatus.WORKING) {
      // If ready to begin updating more instances, evaluate those as well.
      actions.putAll(startNextInstanceGroup(stateProvider));
    }

    return new EvaluationResult<>(computeJobUpdateStatus(), actions.build());
  }