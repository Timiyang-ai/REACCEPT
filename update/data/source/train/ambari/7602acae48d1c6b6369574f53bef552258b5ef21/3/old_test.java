@Test
  public void testFindTargetByName() {
    List<AlertTargetEntity> targets = dao.findAllTargets();
    Assert.assertNotNull(targets);
    AlertTargetEntity target = targets.get(3);

    AlertTargetEntity actual = dao.findTargetByName(target.getTargetName());
    Assert.assertEquals(target, actual);
  }