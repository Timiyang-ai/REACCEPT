  @Test
  public void parse() {
    List<String> definitions = Arrays.asList(
        "write(type)",
        " write(type) ",
        "write(type);",
        "write(type); write(type)",
        "write(type); write(type);"
    );

    parseValidInternal(definitions);

    TransformDefinition.parse("write(hive).option(some.option1, 100).option(some.option2, 2gb);");
  }