public Community[] getCommunities() throws SQLException
    {
        // Get the bundle table rows
        TableRowIterator tri = DatabaseManager.queryTable(ourContext,"community",
                        "SELECT community.* FROM community, community2collection WHERE " +
                        "community.community_id=community2collection.community_id " +
                        "AND community2collection.collection_id= ? ",
                        getID());

        // Build a list of Community objects
        List<Community> communities = new ArrayList<Community>();

        try
        {
            while (tri.hasNext())
            {
                TableRow row = tri.next();

                // First check the cache
                Community owner = (Community) ourContext.fromCache(Community.class,
                        row.getIntColumn("community_id"));

                if (owner == null)
                {
                    owner = new Community(ourContext, row);
                }

                communities.add(owner);

                // now add any parent communities
                Community[] parents = owner.getAllParents();
                communities.addAll(Arrays.asList(parents));
            }
        }
        finally
        {
            // close the TableRowIterator to free up resources
            if (tri != null)
            {
                tri.close();
            }
        }

        Community[] communityArray = new Community[communities.size()];
        communityArray = (Community[]) communities.toArray(communityArray);

        return communityArray;
    }