List<List<Measurement>> getBatches() {
    List<List<Measurement>> batches = new ArrayList<>();
    List<Measurement> ms = getMeasurements().collect(Collectors.toList());
    for (int i = 0; i < ms.size(); i += batchSize) {
      List<Measurement> batch = ms.subList(i, Math.min(ms.size(), i + batchSize));
      batches.add(batch);
    }
    return batches;
  }