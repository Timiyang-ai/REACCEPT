public void vetoed(TaskGroupKey taskGroupKey, Set<Veto> vetoes) {
    fitByGroupKey.getUnchecked(taskGroupKey).maybeUpdate(vetoes);
  }