  @Test
  public void asFactor() {
    Scope.enter();
    try {
      Frame fr = new TestFrameBuilder()
              .withName("testFrame")
              .withColNames("ColA")
              .withVecTypes(Vec.T_STR)
              .withDataForCol(0, ar("yes", "no"))
              .build();
      Scope.track(fr);

      assertTrue(fr.vec(0).isString());

      Frame res = asFactor(fr, "ColA");

      assertTrue(res.vec(0).isCategorical());
      Scope.track(res);
    } finally {
      Scope.exit();
    }
  }