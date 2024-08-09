  @Test
  public void moveFirstTest() {
    Scope.enter();
    try {
      Frame fr = new TestFrameBuilder()
              .withName("testFrame")
              .withColNames("ColA", "ColB", "fold")
              .withVecTypes(Vec.T_CAT, Vec.T_CAT, Vec.T_NUM)
              .withDataForCol(0, ar("a", "b", "c"))
              .withDataForCol(1, ar("d", "e", "f"))
              .withDataForCol(2, ar(3,1,2))
              .build();

      fr.moveFirst(new int[]{1, 2});
      printOutFrameAsTable(fr, false, fr.numRows());
      assertCatVecEquals(cvec("d", "e", "f"), fr.vec(0));
      assertVecEquals(vec(3,1,2), fr.vec(1), 1e-5);
    } finally {
      Scope.exit();
    }
  }