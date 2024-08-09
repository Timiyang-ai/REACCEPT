public CurveSensitivities mergedWith(CurveSensitivities other) {
    return new CurveSensitivities(
        info.combinedWith(other.info),
        mergedWith(other.typedSensitivities).getTypedSensitivities());
  }