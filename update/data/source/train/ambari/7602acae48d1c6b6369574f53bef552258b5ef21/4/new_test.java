@Test
  public void testFindAllTargets() throws Exception {
    List<AlertTargetEntity> targets = dao.findAllTargets();
    assertNotNull(targets);
    assertEquals(5, targets.size());
  }