private List<Map.Entry<Long, Double>> getSortedCRF() {
    List<Map.Entry<Long, Double>> sortedCRF =
        new ArrayList<Map.Entry<Long, Double>>(mBlockIdToCRFValue.entrySet());
    Collections.sort(sortedCRF, new Comparator<Map.Entry<Long, Double>>() {
      @Override
      public int compare(Entry<Long, Double> o1, Entry<Long, Double> o2) {
        double res = o1.getValue() - o2.getValue();
        if (Double.compare(res, 0) < 0) {
          return -1;
        } else if (Double.compare(res, 0) > 0) {
          return 1;
        } else {
          return 0;
        }
      }
    });
    return sortedCRF;
  }