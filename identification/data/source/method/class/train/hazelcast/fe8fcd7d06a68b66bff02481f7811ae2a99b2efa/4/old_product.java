static MembersView cloneAdding(MembersView source, Collection<MemberInfo> newMembers) {
        List<MemberInfo> list = new ArrayList<MemberInfo>(source.size() + newMembers.size());
        list.addAll(source.getMembers());
        list.addAll(newMembers);

        return new MembersView(source.version + 1, list);
    }