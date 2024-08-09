public InterpolatedNodalCurve withNode(int index, CurveParameterMetadata paramMetadata, double x, double y) {
    double[] xExtended = Arrays.copyOf(xValues, xValues.length + 1);
    double[] yExtended = Arrays.copyOf(yValues, yValues.length + 1);
    System.arraycopy(xExtended, index, xExtended, index + 1, xValues.length - index);
    System.arraycopy(yExtended, index, yExtended, index + 1, yValues.length - index);
    xExtended[index] = x;
    yExtended[index] = y;
    // add to existing metadata, or do nothing if no existing metadata
    CurveMetadata md = metadata.getParameterMetadata()
        .map(params -> {
          List<CurveParameterMetadata> extended = new ArrayList<>(params);
          extended.add(index, paramMetadata);
          return DefaultCurveMetadata.of(getName(), extended);
        })
        .orElse(DefaultCurveMetadata.of(getName()));
    return new InterpolatedNodalCurve(md, xExtended, yExtended, extrapolatorLeft, interpolator, extrapolatorRight);
  }