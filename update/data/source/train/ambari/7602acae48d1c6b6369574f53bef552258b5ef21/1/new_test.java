@Test
  public void testFindAllGroups() throws Exception {
    List<AlertGroupEntity> groups = dao.findAllGroups();
    assertNotNull(groups);
    assertEquals(10, groups.size());
  }