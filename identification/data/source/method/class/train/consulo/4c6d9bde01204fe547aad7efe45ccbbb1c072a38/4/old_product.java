public static TokenSet create(IElementType... types) {
    TokenSet set = new TokenSet();
    for (IElementType type : types) {
      if (type != null) {
        final short index = type.getIndex();
        assert index >= 0 : "Unregistered elements are not allowed here: " + LogUtil.objectAndClass(type);
        set.mySet[index] = true;
      }
    }
    return set;
  }