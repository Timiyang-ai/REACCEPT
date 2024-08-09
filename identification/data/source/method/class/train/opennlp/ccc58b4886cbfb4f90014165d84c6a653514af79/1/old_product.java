static int countTruePositives(final Object[] references, final Object[] predictions) {

    List<Object> predListSpans = new ArrayList<Object>(predictions.length);
    Collections.addAll(predListSpans, predictions);
    int truePositives = 0;
    Object matchedItem = null;

    for (int referenceIndex = 0; referenceIndex < references.length; referenceIndex++) {
      Object referenceName = references[referenceIndex];

      for (int predIndex = 0; predIndex < predListSpans.size(); predIndex++) {

        if (referenceName.equals(predListSpans.get(predIndex))) {
          matchedItem = predListSpans.get(predIndex);
          truePositives++;
        }
      }
      if (matchedItem != null) {
        predListSpans.remove(matchedItem);
      }
    }
    return truePositives;
  }