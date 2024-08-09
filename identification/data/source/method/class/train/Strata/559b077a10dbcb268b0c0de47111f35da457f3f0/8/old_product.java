@Override
  public DefaultCurveMetadata withInfo(Map<CurveInfoType<?>, Object> additionalInfo) {
    return toBuilder().addInfo(additionalInfo).build();
  }