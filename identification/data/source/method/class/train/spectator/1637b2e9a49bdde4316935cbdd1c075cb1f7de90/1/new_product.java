Stream<Measurement> getMeasurements() {
    return stream()
        .filter(m -> !m.hasExpired())
        .flatMap(m -> StreamSupport.stream(m.measure().spliterator(), false))
        .filter(m -> !Double.isNaN(m.value()));
  }