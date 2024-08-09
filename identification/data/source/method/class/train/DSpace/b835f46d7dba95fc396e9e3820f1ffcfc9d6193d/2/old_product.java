public static Group[] allMemberGroups(Context c, EPerson e)
            throws SQLException
    {
        List groupList = new ArrayList();

        Set myGroups = allMemberGroupIDs(c, e);
        // now convert those Integers to Groups
        Iterator i = myGroups.iterator();

        while (i.hasNext())
        {
            groupList.add(Group.find(c, ((Integer) i.next()).intValue()));
        }

        return (Group[]) groupList.toArray(new Group[0]);
    }