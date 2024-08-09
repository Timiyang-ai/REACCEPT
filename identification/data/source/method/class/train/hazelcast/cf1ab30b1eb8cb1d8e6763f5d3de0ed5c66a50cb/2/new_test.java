    @Test
    public void cloneExcluding() {
        MemberImpl[] members = newMembers(6);

        MemberImpl exclude0 = members[0];
        MemberImpl exclude1 = new MemberImpl.Builder(newAddress(6000))
                .version(VERSION)
                .uuid(members[1].getUuid())
                .build();
        MemberImpl exclude2 = new MemberImpl.Builder(members[2].getAddress())
                .version(VERSION)
                .uuid(newUnsecureUUID())
                .build();

        MemberMap map = MemberMap.cloneExcluding(MemberMap.createNew(members), exclude0, exclude1, exclude2);

        int numOfExcludedMembers = 3;
        assertEquals(members.length - numOfExcludedMembers, map.getMembers().size());
        assertEquals(members.length - numOfExcludedMembers, map.getAddresses().size());
        assertEquals(members.length - numOfExcludedMembers, map.size());

        for (int i = 0; i < numOfExcludedMembers; i++) {
            MemberImpl member = members[i];
            assertNotContains(map, member.getAddress());
            assertNotContains(map, member.getUuid());
            assertNull(map.getMember(member.getAddress()));
            assertNull(map.getMember(member.getUuid()));
        }

        for (int i = numOfExcludedMembers; i < members.length; i++) {
            MemberImpl member = members[i];
            assertContains(map, member.getAddress());
            assertContains(map, member.getUuid());
            assertSame(member, map.getMember(member.getAddress()));
            assertSame(member, map.getMember(member.getUuid()));
        }

        assertMemberSet(map);
    }