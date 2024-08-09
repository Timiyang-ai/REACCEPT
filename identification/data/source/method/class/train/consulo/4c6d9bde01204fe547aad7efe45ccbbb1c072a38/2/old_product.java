public IElementType[] getTypes() {
    IElementType[] types = myTypes;
    if (types == null) {
      int elementCount = 0;
      for (boolean bit : mySet) {
        if (bit) elementCount++;
      }

      types = new IElementType[elementCount];
      int count = 0;
      for (short i = IElementType.FIRST_TOKEN_INDEX; i < mySet.length; i++) {
        if (mySet[i]) {
          types[count++] = IElementType.find(i);
        }
      }

      myTypes = types;
    }

    return types;
  }