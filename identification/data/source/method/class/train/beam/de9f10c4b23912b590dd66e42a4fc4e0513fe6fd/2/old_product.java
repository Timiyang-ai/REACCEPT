@Override
  public boolean shouldFire(TriggerContext context) throws Exception {
    for (ExecutableTrigger<W> subtrigger : context.trigger().subTriggers()) {
      if (!context.forTrigger(subtrigger).trigger().isFinished()
          && !subtrigger.invokeShouldFire(context)) {
        return false;
      }
    }
    return true;
  }