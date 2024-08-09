public static ConfusionMatrix buildCM(VecAry actuals, VecAry predictions) {
    if (!actuals.isCategorical()) throw new IllegalArgumentException("actuals must be categorical.");
    if (!predictions.isCategorical()) throw new IllegalArgumentException("predictions must be categorical.");
    Scope.enter();
    try {
      VecAry adapted = predictions.adaptTo(actuals.domain());
      int len = actuals.domain().length;
      CMBuilder cm = new CMBuilder(len).doAll(new VecAry(actuals).append(adapted));
      return new ConfusionMatrix(cm._arr, actuals.domain());
    } finally {
      Scope.exit();
    }
  }