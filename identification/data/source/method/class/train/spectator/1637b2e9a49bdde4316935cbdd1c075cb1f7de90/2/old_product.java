List<Measurement> getMeasurements() {
    return stream()
        .flatMap(m -> StreamSupport.stream(m.measure().spliterator(), false))
        .collect(Collectors.toList());
  }