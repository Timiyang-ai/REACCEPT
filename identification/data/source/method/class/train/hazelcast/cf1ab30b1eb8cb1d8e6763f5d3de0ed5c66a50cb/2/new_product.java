static MemberMap cloneExcluding(MemberMap source, MemberImpl... excludeMembers) {
        if (source.size() == 0) {
            return source;
        }

        Map<Address, MemberImpl> addressMap = new LinkedHashMap<>(source.addressToMemberMap);
        Map<UUID, MemberImpl> uuidMap = new LinkedHashMap<>(source.uuidToMemberMap);

        for (MemberImpl member : excludeMembers) {
            MemberImpl removed = addressMap.remove(member.getAddress());
            if (removed != null) {
                uuidMap.remove(removed.getUuid());
            }

            removed = uuidMap.remove(member.getUuid());
            if (removed != null) {
                addressMap.remove(removed.getAddress());
            }
        }

        return new MemberMap(source.version + excludeMembers.length, addressMap, uuidMap);
    }