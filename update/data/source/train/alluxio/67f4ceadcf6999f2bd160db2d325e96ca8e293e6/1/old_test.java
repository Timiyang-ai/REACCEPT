@Test
  public void removeByFieldTest() {
    Assert.assertEquals(3, mSet.getByField(mIntIndex, 1).size());
    Assert.assertEquals(9, mSet.size());
    Assert.assertTrue(mSet.removeByField(mIntIndex, 1));
    Assert.assertEquals(6, mSet.size());
    Assert.assertEquals(0, mSet.getByField(mIntIndex, 1).size());
    Assert.assertEquals(3, mSet.getByField(mIntIndex, 0).size());
    Assert.assertEquals(3, mSet.getByField(mIntIndex, 2).size());
    for (long l = 0; l < 3; l++) {
      Assert.assertEquals(2, mSet.getByField(mLongIndex, l).size());
    }
  }