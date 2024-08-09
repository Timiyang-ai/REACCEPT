  @Test
  public void getColumnIndexByName() {
    Scope.enter();
    try {

      Frame fr = new TestFrameBuilder()
              .withName("testFrame")
              .withColNames("ColA", "ColB")
              .withVecTypes(Vec.T_CAT, Vec.T_NUM)
              .withDataForCol(0, ar("a", "b"))
              .withDataForCol(1, ard(1, 1))
              .build();
      Scope.track(fr);

      assertEquals(0, fr.find("ColA"));
      assertEquals(1, fr.find("ColB"));

    } finally {
      Scope.exit();
    }
  }