  @Test
  public void withRandomBinaryDataForColTest(){
    long seed = 44L;
    Frame fr = new TestFrameBuilder()
            .withName("testFrame")
            .withColNames("ColA")
            .withVecTypes(Vec.T_CAT)
            .withRandomBinaryDataForCol(0, 1000, seed)
            .build();

    assertEquals(2, fr.vec("ColA").cardinality());

    fr.delete();
  }