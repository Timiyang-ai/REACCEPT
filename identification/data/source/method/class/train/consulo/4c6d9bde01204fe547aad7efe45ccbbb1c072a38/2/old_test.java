  @Test
  public void getTypes() throws Exception {
    assertArrayEquals(IElementType.EMPTY_ARRAY, TokenSet.EMPTY.getTypes());
    assertArrayEquals(new IElementType[]{T1, T2}, S12.getTypes());
    assertArrayEquals(new IElementType[]{T3, T4}, S34.getTypes());
    assertEquals("[]", TokenSet.EMPTY.toString());
    assertEquals("[T1, T2]", S12.toString());
    assertEquals("[T3, T4]", S34.toString());
  }