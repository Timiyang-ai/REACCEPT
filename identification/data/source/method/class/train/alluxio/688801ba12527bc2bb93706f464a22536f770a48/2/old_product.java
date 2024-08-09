private List<Map.Entry<Long, Double>> getSortedCRF() {
    List<Map.Entry<Long, Double>> sortedCRF = new ArrayList<>(mBlockIdToCRFValue.entrySet());
    Collections.sort(sortedCRF, Comparator.comparingDouble(Entry::getValue));
    return sortedCRF;
  }