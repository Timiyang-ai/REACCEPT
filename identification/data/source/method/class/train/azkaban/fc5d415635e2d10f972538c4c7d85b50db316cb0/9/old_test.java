  @Test
  public void writePropsNoJarDependencyTest1() throws IOException {
    final Map<String, String> test = new HashMap<>();
    test.put("\"myTest\n\b", "myValue\t\\");
    test.put("normalKey", "Other key");

    final StringWriter writer = new StringWriter();
    JSONUtils.writePropsNoJarDependency(test, writer);

    final String jsonStr = writer.toString();
    System.out.println(writer.toString());

    final Map<String, String> result =
        (Map<String, String>) JSONUtils.parseJSONFromString(jsonStr);
    checkInAndOut(test, result);
  }