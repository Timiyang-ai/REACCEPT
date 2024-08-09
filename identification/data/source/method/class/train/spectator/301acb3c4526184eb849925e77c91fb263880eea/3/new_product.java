List<List<Measurement>> getBatches() {
    List<Measurement> ms = getMeasurements().collect(Collectors.toList());
    debugRegistry.distributionSummary("spectator.registrySize").record(ms.size());

    List<List<Measurement>> batches = new ArrayList<>();
    for (int i = 0; i < ms.size(); i += batchSize) {
      List<Measurement> batch = ms.subList(i, Math.min(ms.size(), i + batchSize));
      batches.add(batch);
    }
    return batches;
  }