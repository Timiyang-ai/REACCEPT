@Test
  public void testFindAllGroups() {
    List<AlertGroupEntity> groups = dao.findAllGroups();
    Assert.assertNotNull(groups);
    Assert.assertEquals(10, groups.size());
  }