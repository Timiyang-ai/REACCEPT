@Test
  public void testFindGroupByName() {
    List<AlertGroupEntity> groups = dao.findAllGroups();
    Assert.assertNotNull(groups);
    AlertGroupEntity group = groups.get(3);

    AlertGroupEntity actual = dao.findGroupByName(group.getClusterId(),
        group.getGroupName());

    Assert.assertEquals(group, actual);
  }