@Deprecated
  public static boolean equivalent(
      @Nullable Network<?, ?> networkA, @Nullable Network<?, ?> networkB) {
    return Objects.equal(networkA, networkB);
  }