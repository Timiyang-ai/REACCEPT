@Test
  public void test000Put() throws Exception {
    String regionname = "testPut";
    int localMaxMemory = 0;
    System.setProperty(PartitionedRegion.RETRY_TIMEOUT_PROPERTY, "20000");
    PartitionedRegion pr = (PartitionedRegion) PartitionedRegionTestHelper
        .createPartitionedRegion(regionname, String.valueOf(localMaxMemory), 0);
    System.setProperty(PartitionedRegion.RETRY_TIMEOUT_PROPERTY,
        Integer.toString(PartitionedRegionHelper.DEFAULT_TOTAL_WAIT_RETRY_ITERATION));
    final String expectedExceptions = PartitionedRegionStorageException.class.getName();
    logger.info("<ExpectedException action=add>" + expectedExceptions + "</ExpectedException>");
    try {
      pr.put(new Integer(1), val);
      fail("testPut()- Expected PartitionedRegionException not thrown for localMaxMemory = 0");
    } catch (PartitionedRegionStorageException ex) {
      if (logWriter.fineEnabled()) {
        logWriter.fine(
            "testPut() - Got a correct exception-PartitionedRegionStorageException for localMaxMemory=0 ");
      }
    }
    logger.info("<ExpectedException action=remove>" + expectedExceptions + "</ExpectedException>");

    if (!pr.isDestroyed())
      pr.destroyRegion();

    pr = (PartitionedRegion) PartitionedRegionTestHelper.createPartitionedRegion(regionname,
        String.valueOf(400), 0);
    final int maxEntries = 3;
    for (int num = 0; num < maxEntries; num++) {
      final Integer key = new Integer(num);
      final Object oldVal = pr.put(key, this.val);
      // Assert a more generic return value here because the bucket has not been allocated yet
      // thus do not know if the value is local or not
      assertTrue(oldVal == null);
      assertEquals(this.val, pr.get(key));

      final Region.Entry entry = pr.getEntry(key);
      assertNotNull(entry);
      assertEquals(this.val, entry.getValue());
      assertTrue(pr.values().contains(this.val));
      if (RegionTestCase.entryIsLocal(entry)) {
        assertEquals("Failed for key " + num, this.val, pr.put(key, key));
      } else {
        assertEquals("Failed for key " + num, null, pr.put(key, key));
      }
      assertEquals((num + 1) * 2, ((GemFireCacheImpl) pr.getCache()).getCachePerfStats().getPuts());
    }

    if (!pr.isDestroyed())
      pr.destroyRegion();

    pr = (PartitionedRegion) PartitionedRegionTestHelper.createPartitionedRegion(regionname,
        String.valueOf(400), 0);

    for (int num = 0; num < maxEntries; num++) {
      pr.put(new Integer(num), this.val);
      Object retval = pr.get(new Integer(num));
      assertEquals(this.val, retval);
    }

    for (int num = 0; num < maxEntries; num++) {
      if (RegionTestCase.entryIsLocal(pr.getEntry(new Integer(num)))) {
        assertEquals(this.val, pr.put(new Integer(num), this.val));
      } else {
        assertEquals(null, pr.put(new Integer(num), this.val));
      }
    }

    final Object dummyVal = "DummyVal";
    for (int num = 0; num < maxEntries; num++) {
      final Object getObj = pr.get(new Integer(num));
      final Object oldPut = pr.put(new Integer(num), dummyVal);
      if (((EntrySnapshot) pr.getEntry(new Integer(num))).wasInitiallyLocal()) {
        assertEquals("Returned value from put operation is not same as the old value", getObj,
            oldPut);

      } else {
        assertEquals(null, oldPut);
      }
      assertEquals("testPut()- error in putting the value in the Partitioned Region", dummyVal,
          pr.get(new Integer(num)));
    }
  }