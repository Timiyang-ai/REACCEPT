  @Test public void mapKeyAndValueTypes() throws Exception {
    Type mapOfStringIntegerType = TypesTest.class.getDeclaredField(
        "mapOfStringInteger").getGenericType();
    assertThat(Types.mapKeyAndValueTypes(mapOfStringIntegerType, Map.class))
        .containsExactly(String.class, Integer.class);
  }