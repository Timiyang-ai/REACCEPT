public T parse(String name) {
    ArgChecker.notNull(name, "name");
    T value = parseMap.get(name);
    if (value == null) {
      throw new IllegalArgumentException(
          Messages.format("Unknown enum name '{}' for type {}, valid values are {}", name, enumType.getName(), formattedSet));
    }
    return value;
  }