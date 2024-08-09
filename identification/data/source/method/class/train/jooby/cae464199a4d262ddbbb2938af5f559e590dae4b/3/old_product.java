default <T extends Enum<T>> T toEnum(Throwing.Function<String, T> fn,
      Function<String, String> caseFormatter) {
    return fn.apply(caseFormatter.apply(value()));
  }