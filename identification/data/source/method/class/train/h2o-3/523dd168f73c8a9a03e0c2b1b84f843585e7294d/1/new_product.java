public static ConfusionMatrix buildCM(Vec actuals, Vec predictions) {
    if (!actuals.isEnum()) throw new IllegalArgumentException("actuals must be enum.");
    if (!predictions.isEnum()) throw new IllegalArgumentException("predictions must be enum.");
    Scope.enter();
    try {
      Vec adapted = predictions.adaptTo(actuals.domain());
      int len = actuals.domain().length;
      CMBuilder cm = new CMBuilder(len).doAll(actuals, adapted);
      return new ConfusionMatrix(cm._arr, actuals.domain());
    } finally {
      Scope.exit();
    }
  }