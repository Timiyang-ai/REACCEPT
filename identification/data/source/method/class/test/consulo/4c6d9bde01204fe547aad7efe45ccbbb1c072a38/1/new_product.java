@NotNull
  public static TokenSet create(@NotNull IElementType... types) {
    if (types.length == 0) return EMPTY;
    if (types.length == 1 && types[0] == TokenType.WHITE_SPACE) {
      return WHITE_SPACE;
    }
    return doCreate(types);
  }