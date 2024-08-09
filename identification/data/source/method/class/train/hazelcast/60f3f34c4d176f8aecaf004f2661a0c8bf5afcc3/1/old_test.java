    @Test
    public void createNew() throws Exception {
        int version = 7;
        MemberImpl[] members = MemberMapTest.newMembers(5);
        MembersView view = MembersView.createNew(version, Arrays.asList(members));

        assertEquals(version, view.getVersion());
        assertMembersViewEquals(members, view);
    }