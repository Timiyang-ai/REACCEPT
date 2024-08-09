  @Test public void collectionElementType() throws Exception {
    Type arrayListOfMapOfStringIntegerType = TypesTest.class.getDeclaredField(
        "arrayListOfMapOfStringInteger").getGenericType();
    Type mapOfStringIntegerType = TypesTest.class.getDeclaredField(
        "mapOfStringInteger").getGenericType();
    assertThat(Types.collectionElementType(arrayListOfMapOfStringIntegerType, List.class))
        .isEqualTo(mapOfStringIntegerType);
  }