public InterpolatedNodalCurve withNode(int index, ParameterMetadata paramMetadata, double x, double y) {
    DoubleArray xExtended = xValues.subArray(0, index).concat(new double[] {x}).concat(xValues.subArray(index));
    DoubleArray yExtended = yValues.subArray(0, index).concat(new double[] {y}).concat(yValues.subArray(index));
    // add to existing metadata, or do nothing if no existing metadata
    CurveMetadata md = metadata.getParameterMetadata()
        .map(params -> {
          List<ParameterMetadata> extended = new ArrayList<>(params);
          extended.add(index, paramMetadata);
          return metadata.withParameterMetadata(extended);
        })
        .orElse(metadata);
    return new InterpolatedNodalCurve(md, xExtended, yExtended, extrapolatorLeft, interpolator, extrapolatorRight);
  }