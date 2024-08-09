  @Test public void newParameterizedType() throws Exception {
    // List<A>. List is a top-level class.
    Type type = Types.newParameterizedType(List.class, A.class);
    assertThat(getFirstTypeArgument(type)).isEqualTo(A.class);

    // A<B>. A is a static inner class.
    type = Types.newParameterizedTypeWithOwner(TypesTest.class, A.class, B.class);
    assertThat(getFirstTypeArgument(type)).isEqualTo(B.class);
  }