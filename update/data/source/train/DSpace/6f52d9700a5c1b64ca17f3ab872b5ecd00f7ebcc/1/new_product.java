public List<Community> getCommunities() throws SQLException
    {
        Collections.sort(communities, new NameAscendingComparator());
        return communities;
    }