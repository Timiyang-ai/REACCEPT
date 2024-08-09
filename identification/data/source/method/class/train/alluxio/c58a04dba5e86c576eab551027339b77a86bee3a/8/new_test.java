  @Test
  public void remove() {
    Pair toRemove = mSet.getFirstByField(mUniqueLongIndex, 1L);
    assertEquals(1, mSet.getByField(mUniqueLongIndex, toRemove.longValue()).size());
    assertEquals(9, mSet.size());
    assertTrue(mSet.remove(toRemove));
    assertEquals(8, mSet.size());

    assertEquals(2, mSet.getByField(mNonUniqueIntIndex, toRemove.intValue()).size());
    assertTrue("Element should be in the NonUniqueIntIndex",
        mSet.contains(mNonUniqueIntIndex, toRemove.intValue()));

    assertEquals(0, mSet.getByField(mUniqueLongIndex, toRemove.longValue()).size());
    assertFalse("Element should not be in the mUniqueLongIndex",
        mSet.contains(mUniqueLongIndex, toRemove.longValue()));

    toRemove = mSet.getFirstByField(mNonUniqueIntIndex, 2);
    assertTrue(mSet.remove(toRemove));
    assertTrue("Element should be in the NonUniqueIntIndex",
        mSet.contains(mNonUniqueIntIndex, toRemove.intValue()));

    toRemove = mSet.getFirstByField(mNonUniqueIntIndex, 2);
    assertTrue(mSet.remove(toRemove));
    assertTrue("Element should be in the NonUniqueIntIndex",
        mSet.contains(mNonUniqueIntIndex, toRemove.intValue()));

    toRemove = mSet.getFirstByField(mNonUniqueIntIndex, 2);
    assertTrue(mSet.remove(toRemove));
    assertFalse("Element should not be in the NonUniqueIntIndex",
        mSet.contains(mNonUniqueIntIndex, toRemove.intValue()));

    toRemove = mSet.getFirstByField(mNonUniqueIntIndex, 2);
    assertTrue(toRemove == null);
  }