    @Test
    public void createNew() {
        MemberImpl[] members = newMembers(5);

        MemberMap map = MemberMap.createNew(members);
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