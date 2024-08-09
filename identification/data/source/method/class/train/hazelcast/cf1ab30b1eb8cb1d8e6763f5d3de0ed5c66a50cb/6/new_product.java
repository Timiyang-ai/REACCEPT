static MemberMap createNew(int version, MemberImpl... members) {
        Map<Address, MemberImpl> addressMap = createLinkedHashMap(members.length);
        Map<UUID, MemberImpl> uuidMap = createLinkedHashMap(members.length);

        for (MemberImpl member : members) {
            putMember(addressMap, uuidMap, member);
        }

        return new MemberMap(version, addressMap, uuidMap);
    }