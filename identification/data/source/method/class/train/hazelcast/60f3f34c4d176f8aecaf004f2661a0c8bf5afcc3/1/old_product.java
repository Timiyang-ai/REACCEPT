public static MembersView createNew(int version, Collection<MemberImpl> members) {
        List<MemberInfo> list = new ArrayList<MemberInfo>(members.size());

        for (MemberImpl member : members) {
            list.add(new MemberInfo(member));
        }

        return new MembersView(version, unmodifiableList(list));
    }