@Test
  public void nonUniqueRemoveByFieldTest() {
    Assert.assertEquals(3, mSet.getByField(mNonUniqueIntIndex, 1).size());
    Assert.assertEquals(9, mSet.size());
    Assert.assertEquals(3, mSet.removeByField(mNonUniqueIntIndex, 1));
    Assert.assertEquals(6, mSet.size());
    Assert.assertEquals(0, mSet.getByField(mNonUniqueIntIndex, 1).size());
    Assert.assertEquals(3, mSet.getByField(mNonUniqueIntIndex, 0).size());
    Assert.assertEquals(3, mSet.getByField(mNonUniqueIntIndex, 2).size());
    for (long l = 3; l < 6; l++) {
      Assert.assertEquals(0, mSet.getByField(mUniqueLongIndex, l).size());
    }
  }