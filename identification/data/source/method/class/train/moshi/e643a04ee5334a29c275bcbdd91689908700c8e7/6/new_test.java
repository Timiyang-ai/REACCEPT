  @Test public void subtypeOf() throws Exception {
    Type listOfWildcardType = TypesTest.class.getDeclaredField("listSubtype").getGenericType();
    Type expected = Types.collectionElementType(listOfWildcardType, List.class);
    assertThat(Types.subtypeOf(CharSequence.class)).isEqualTo(expected);
  }