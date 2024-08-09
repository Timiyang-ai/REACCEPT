@NotNull
  public IElementType[] getTypes() {
    IElementType[] types = myTypes;

    if (types == null) {
      if (myWords.length > 0) {
        int elementCount = 0;
        for (long word : myWords) {
          elementCount += Long.bitCount(word);
        }

        types = new IElementType[elementCount];
        int count = 0;
        for (short i = (short)(myShift << 6); i <= myMax; i++) {
          if (get(i)) {
            types[count++] = IElementType.find(i);
          }
        }
      }
      else {
        types = IElementType.EMPTY_ARRAY;
      }

      myTypes = types;
    }

    return types;
  }