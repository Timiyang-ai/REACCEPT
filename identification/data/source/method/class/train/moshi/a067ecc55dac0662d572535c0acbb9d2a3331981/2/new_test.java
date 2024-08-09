  @Test public void arrayComponentType() throws Exception {
    assertThat(Types.arrayComponentType(String[][].class)).isEqualTo(String[].class);
    assertThat(Types.arrayComponentType(String[].class)).isEqualTo(String.class);

    Type arrayOfMapOfStringIntegerType = TypesTest.class.getDeclaredField(
        "arrayOfMapOfStringInteger").getGenericType();
    Type mapOfStringIntegerType = TypesTest.class.getDeclaredField(
        "mapOfStringInteger").getGenericType();
    assertThat(Types.arrayComponentType(arrayOfMapOfStringIntegerType))
        .isEqualTo(mapOfStringIntegerType);
  }