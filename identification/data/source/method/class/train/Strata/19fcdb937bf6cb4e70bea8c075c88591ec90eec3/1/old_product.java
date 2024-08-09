public Stream<Curve> stream() {
    return Stream.concat(discountCurves.values().stream(), forwardCurves.values().stream());
  }