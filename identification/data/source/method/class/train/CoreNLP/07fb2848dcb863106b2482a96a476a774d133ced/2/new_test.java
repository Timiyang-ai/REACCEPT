  @Test
  public void testgetSubListIndex() {
    String[] t1 = {"this", "is", "test"};
    String[] t2 = {"well","this","is","not","this","is","test","also"};
    assertEquals(4,(ArrayUtils.getSubListIndex(t1, t2).get(0).intValue()));
    String[] t3 = {"cough","increased"};
    String[] t4 = {"i","dont","really","cough"};
    assertEquals(0, ArrayUtils.getSubListIndex(t3, t4).size());
    String[] t5 = {"cough","increased"};
    String[] t6 = {"cough","aggravated"};
    assertEquals(0, ArrayUtils.getSubListIndex(t5, t6).size());
    String[] t7 = {"cough","increased"};
    String[] t8 = {"cough","aggravated","cough","increased","and","cough", "increased","and","cough","and","increased"};
    assertEquals(2, ArrayUtils.getSubListIndex(t7, t8).get(0).intValue());
    assertEquals(5, ArrayUtils.getSubListIndex(t7, t8).get(1).intValue());
  }