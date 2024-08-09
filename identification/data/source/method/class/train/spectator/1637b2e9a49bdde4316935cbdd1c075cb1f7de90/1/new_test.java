  private List<Measurement> getMeasurements() {
    return registry.measurements().collect(Collectors.toList());
  }