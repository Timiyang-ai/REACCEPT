static MembersView cloneAdding(MembersView source, Collection<MemberInfo> newMembers) {
        List<MemberInfo> list = new ArrayList<MemberInfo>(source.size() + newMembers.size());
        list.addAll(source.getMembers());
        int newVersion = max(source.version, source.size());
        for (MemberInfo newMember : newMembers) {
            MemberInfo m = new MemberInfo(newMember.getAddress(), newMember.getUuid(), newMember.getAttributes(),
                    newMember.isLiteMember(), newMember.getVersion(), ++newVersion);
            list.add(m);
        }

        return new MembersView(newVersion, unmodifiableList(list));
    }