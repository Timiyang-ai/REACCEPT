@NotNull
  public static TokenSet create(@NotNull IElementType... types) {
    if (types.length == 0) return EMPTY;

    short min = Short.MAX_VALUE;
    short max = 0;
    for (IElementType type : types) {
      if (type != null) {
        final short index = type.getIndex();
        assert index >= 0 : "Unregistered elements are not allowed here: " + LogUtil.objectAndClass(type);
        if (min > index) min = index;
        if (max < index) max = index;
      }
    }

    final short shift = (short)(min >> 6);
    final TokenSet set = new TokenSet(shift, max);
    for (IElementType type : types) {
      if (type != null) {
        final short index = type.getIndex();
        final int wordIndex = (index >> 6) - shift;
        set.myWords[wordIndex] |= 1L << index;
      }
    }
    return set;
  }