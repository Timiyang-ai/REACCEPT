static int countTruePositives(final Object[] references, final Object[] predictions) {

    List<Object> predListSpans = new ArrayList<>(predictions.length);
    Collections.addAll(predListSpans, predictions);
    int truePositives = 0;
    Object matchedItem = null;

    for (Object referenceName : references) {
      for (Object predListSpan : predListSpans) {
        if (referenceName.equals(predListSpan)) {
          matchedItem = predListSpan;
          truePositives++;
        }
      }
      if (matchedItem != null) {
        predListSpans.remove(matchedItem);
      }
    }
    return truePositives;
  }