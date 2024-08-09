  @Test public void supertypeOf() throws Exception {
    Type listOfWildcardType = TypesTest.class.getDeclaredField("listSupertype").getGenericType();
    Type expected = Types.collectionElementType(listOfWildcardType, List.class);
    assertThat(Types.supertypeOf(String.class)).isEqualTo(expected);
  }