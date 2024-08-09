private List<Map.Entry<Long, Double>> getSortedCRF() {
    List<Map.Entry<Long, Double>> sortedCRF =
        new ArrayList<Map.Entry<Long, Double>>(mBlockIdToCRFValue.entrySet());
    Collections.sort(sortedCRF, new Comparator<Map.Entry<Long, Double>>() {
      @Override
      public int compare(Entry<Long, Double> o1, Entry<Long, Double> o2) {
        return Double.compare(o1.getValue(), o2.getValue());
      }
    });
    return sortedCRF;
  }