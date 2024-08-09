@Nonnull default <T extends Enum<T>> T toEnum(@Nonnull SneakyThrows.Function<String, T> fn,
      @Nonnull Function<String, String> nameProvider) {
    return fn.apply(nameProvider.apply(value()));
  }