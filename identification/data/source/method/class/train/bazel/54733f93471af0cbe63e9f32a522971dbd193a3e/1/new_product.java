public static <T extends Enum<T>> EnumSet<T> fromBits(int value, Class<T> clazz) {
    T[] elements = clazz.getEnumConstants();
    Preconditions.checkArgument(elements.length <= 32);
    return Arrays.stream(elements)
        .filter(element -> (value & (1 << element.ordinal())) != 0)
        .collect(toCollection(() -> EnumSet.noneOf(clazz)));
  }