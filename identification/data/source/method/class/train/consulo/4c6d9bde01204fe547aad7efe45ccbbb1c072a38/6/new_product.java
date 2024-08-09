@NotNull
  public IElementType[] getTypes() {
    IElementType[] types = myTypes;

    if (types == null) {
      if (myWords.length == 0) {
        types = IElementType.EMPTY_ARRAY;
      }
      else {
        List<IElementType> list = new ArrayList<>();
        for (short i = (short)Math.max(1, myShift << 6); i <= myMax; i++) {
          if (!get(i)) continue;
          IElementType type = IElementType.find(i);
          if (type != null) {
            list.add(type);
          }
        }
        types = list.toArray(new IElementType[list.size()]);
      }
      myTypes = types;
    }

    return types;
  }