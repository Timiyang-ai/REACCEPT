@Override
  public InterpolatedNodalCurve withNode(double x, double y, ParameterMetadata paramMetadata) {
    int index = Arrays.binarySearch(xValues.toArrayUnsafe(), x);
    if (index >= 0) {
      CurveMetadata md = metadata.getParameterMetadata()
          .map(params -> {
            List<ParameterMetadata> extended = new ArrayList<>(params);
            extended.set(index, paramMetadata);
            return metadata.withParameterMetadata(extended);
          })
          .orElse(metadata);
      DoubleArray yUpdated = yValues.with(index, y);
      return new InterpolatedNodalCurve(md, xValues, yUpdated, interpolator, extrapolatorLeft, extrapolatorRight);
    }
    int insertion = -(index + 1);
    DoubleArray xExtended = xValues.subArray(0, insertion).concat(x).concat(xValues.subArray(insertion));
    DoubleArray yExtended = yValues.subArray(0, insertion).concat(y).concat(yValues.subArray(insertion));
    // add to existing metadata, or do nothing if no existing metadata
    CurveMetadata md = metadata.getParameterMetadata()
        .map(params -> {
          List<ParameterMetadata> extended = new ArrayList<>(params);
          extended.add(insertion, paramMetadata);
          return metadata.withParameterMetadata(extended);
        })
        .orElse(metadata);
    return new InterpolatedNodalCurve(md, xExtended, yExtended, interpolator, extrapolatorLeft, extrapolatorRight);
  }