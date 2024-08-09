  @Test
  public void pushBlock() {
    boolean isUnlinked = false;
    try {
      dbManager.pushBlock(blockCapsule2);
    } catch (UnLinkedBlockException e) {
      isUnlinked = true;
    } catch (Exception e) {
      Assert.assertTrue("pushBlock is error", false);
    }

    if (isUnlinked) {
      Assert.assertEquals("getBlockIdByNum is error",
          dbManager.getHeadBlockNum(), 0);
    } else {
      try {
        Assert.assertEquals(
            "getBlockIdByNum is error",
            blockCapsule2.getBlockId().toString(),
            dbManager.getBlockIdByNum(1).toString());
      } catch (ItemNotFoundException e) {
        e.printStackTrace();
      }
    }

    Assert.assertTrue("hasBlocks is error", dbManager.hasBlocks());
  }