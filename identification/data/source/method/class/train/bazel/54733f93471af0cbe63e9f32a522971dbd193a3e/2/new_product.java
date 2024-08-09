public static <T extends Enum<T>> EnumSet<T> fromBits(int value, Class<T> clazz) {
    T[] elements = clazz.getEnumConstants();
    Preconditions.checkArgument(elements.length <= 32);
    ArrayList<T> result = new ArrayList<>();
    for (T element : elements) {
      if ((value & (1 << element.ordinal())) != 0) {
        result.add(element);
      }
    }

    return newEnumSet(result, clazz);
  }