@Override
  public Stream<Curve> stream() {
    return Stream.concat(repoCurves.values().stream(), issuerCurves.values().stream());
  }