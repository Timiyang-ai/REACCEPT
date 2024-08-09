public static ConfusionMatrix buildCM(Vec actuals, Vec predictions) {
    if (!actuals.isEnum()) throw new IllegalArgumentException("actuals must be enum.");
    if (!predictions.isEnum()) throw new IllegalArgumentException("predictions must be enum.");
    int len = predictions.domain().length;
    CMBuilder cm = new CMBuilder(len).doAll(actuals,predictions);
    return new ConfusionMatrix(cm._arr, predictions.domain());
  }