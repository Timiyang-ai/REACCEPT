static MemberMap createNew(MemberImpl... members) {
        Map<Address, MemberImpl> addressMap = new LinkedHashMap<Address, MemberImpl>();
        Map<String, MemberImpl> uuidMap = new LinkedHashMap<String, MemberImpl>();

        for (MemberImpl member : members) {
            putMember(addressMap, uuidMap, member);
        }

        return new MemberMap(addressMap, uuidMap);
    }