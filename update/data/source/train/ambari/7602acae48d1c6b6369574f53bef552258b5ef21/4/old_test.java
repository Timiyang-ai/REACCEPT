@Test
  public void testFindAllTargets() {
    List<AlertTargetEntity> targets = dao.findAllTargets();
    Assert.assertNotNull(targets);
    Assert.assertEquals(5, targets.size());
  }