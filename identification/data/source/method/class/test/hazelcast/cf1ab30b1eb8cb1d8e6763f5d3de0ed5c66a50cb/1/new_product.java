static MemberMap cloneAdding(MemberMap source, MemberImpl... newMembers) {
        Map<Address, MemberImpl> addressMap = new LinkedHashMap<Address, MemberImpl>(source.addressToMemberMap);
        Map<String, MemberImpl> uuidMap = new LinkedHashMap<String, MemberImpl>(source.uuidToMemberMap);

        for (MemberImpl member : newMembers) {
            putMember(addressMap, uuidMap, member);
        }

        return new MemberMap(source.version + 1, addressMap, uuidMap);
    }