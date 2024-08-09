@Test(expected = SQLException.class)
    public void testSetName_permanent()
            throws Exception
    {
        System.out.println("setName on a 'permanent' Group");
        String name = "NOTANONYMOUS";
        GroupService groupService = EPersonServiceFactory.getInstance().getGroupService();
        Group group = groupService.findByName(context, Group.ANONYMOUS);
        groupService.setName(context, group, name);
    }