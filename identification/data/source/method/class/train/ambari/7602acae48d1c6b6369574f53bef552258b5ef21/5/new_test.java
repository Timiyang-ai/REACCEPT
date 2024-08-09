@Test
  public void testFindTargetByName() throws Exception {
    List<AlertTargetEntity> targets = dao.findAllTargets();
    assertNotNull(targets);
    AlertTargetEntity target = targets.get(3);

    AlertTargetEntity actual = dao.findTargetByName(target.getTargetName());
    assertEquals(target, actual);
  }