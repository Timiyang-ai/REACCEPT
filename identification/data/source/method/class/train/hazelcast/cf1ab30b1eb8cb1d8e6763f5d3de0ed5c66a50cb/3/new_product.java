static MemberMap cloneAdding(MemberMap source, MemberImpl... newMembers) {
        Map<Address, MemberImpl> addressMap = new LinkedHashMap<>(source.addressToMemberMap);
        Map<UUID, MemberImpl> uuidMap = new LinkedHashMap<>(source.uuidToMemberMap);

        for (MemberImpl member : newMembers) {
            putMember(addressMap, uuidMap, member);
        }

        return new MemberMap(source.version + newMembers.length, addressMap, uuidMap);
    }