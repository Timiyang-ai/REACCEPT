@SuppressWarnings("unchecked")
  @Override
  public <T> Optional<T> findInfo(SurfaceInfoType<T> type) {
    return Optional.ofNullable((T) info.get(type));
  }