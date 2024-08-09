    @Test
    public void cloneAdding() {
        MemberImpl[] members = newMembers(5);

        MemberMap map = MemberMap.cloneAdding(MemberMap.createNew(members[0], members[1], members[2]), members[3], members[4]);
        assertEquals(members.length, map.getMembers().size());
        assertEquals(members.length, map.getAddresses().size());
        assertEquals(members.length, map.size());

        for (MemberImpl member : members) {
            assertContains(map, member.getAddress());
            assertContains(map, member.getUuid());
            assertSame(member, map.getMember(member.getAddress()));
            assertSame(member, map.getMember(member.getUuid()));
        }

        assertMemberSet(map);
    }