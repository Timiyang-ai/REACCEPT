@Test
  public void testFindGroupByName() throws Exception {
    List<AlertGroupEntity> groups = dao.findAllGroups();
    assertNotNull(groups);
    AlertGroupEntity group = groups.get(3);

    AlertGroupEntity actual = dao.findGroupByName(group.getClusterId(),
        group.getGroupName());

    assertEquals(group, actual);
  }