    @Test
    public void cloneAdding() throws Exception {
        int version = 6;
        MemberImpl[] members = MemberMapTest.newMembers(4);
        List<MemberInfo> additionalMembers
                = Arrays.asList(new MemberInfo(newMember(6000)), new MemberInfo(newMember(7000)));

        MembersView view =
                MembersView.cloneAdding(MembersView.createNew(version, Arrays.asList(members)), additionalMembers);

        assertEquals(version + additionalMembers.size(), view.getVersion());

        MemberImpl[] newMembers = Arrays.copyOf(members, members.length + additionalMembers.size());
        for (int i = 0; i < additionalMembers.size(); i++) {
            newMembers[members.length + i] = additionalMembers.get(i).toMember();
        }

        assertMembersViewEquals(newMembers, view);
    }