public static Group[] allMemberGroups(Context c, EPerson e)
            throws SQLException
    {
        List<Group> groupList = new ArrayList<Group>();

        Set<Integer> myGroups = allMemberGroupIDs(c, e);
        // now convert those Integers to Groups
        Iterator<Integer> i = myGroups.iterator();

        while (i.hasNext())
        {
            groupList.add(Group.find(c, (i.next()).intValue()));
        }

        return groupList.toArray(new Group[groupList.size()]);
    }