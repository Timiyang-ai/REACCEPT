  @Test
  public void withRandomIntDataForColTest(){
    long seed = 44L;
    int size = 1000;
    int min = 1;
    int max = 5;

    Frame fr = new TestFrameBuilder()
            .withName("testFrame")
            .withColNames("ColA")
            .withVecTypes(Vec.T_NUM)
            .withRandomIntDataForCol(0, size, min, max, seed)
            .build();


    printOutFrameAsTable(fr, false, size);
    Vec generatedVec = fr.vec(0);
    for(int i = 0; i < size; i++) {
      assertTrue(generatedVec.at(i) <= max && generatedVec.at(i) >= min);
    }

    fr.delete();
  }