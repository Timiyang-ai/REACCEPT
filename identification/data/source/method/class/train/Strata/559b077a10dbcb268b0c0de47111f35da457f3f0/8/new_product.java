@Override
  public <T> DefaultCurveMetadata withInfo(CurveInfoType<T> type, T value) {
    return toBuilder().addInfo(type, value).build();
  }