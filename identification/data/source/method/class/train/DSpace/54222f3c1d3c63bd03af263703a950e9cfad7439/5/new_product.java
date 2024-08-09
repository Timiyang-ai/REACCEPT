public Community[] getSubcommunities() throws SQLException
    {
        List<Community> subcommunities = new ArrayList<Community>();

        // Get the table rows
        TableRowIterator tri = null;
        try {
            String query = "SELECT c.* FROM community2community c2c, community c " +
                    "LEFT JOIN metadatavalue m on (m.resource_id = c.community_id and m.resource_type_id = ? and m.metadata_field_id = ?) " +
                    "WHERE c2c.child_comm_id=c.community_id " +
                    "AND c2c.parent_comm_id= ? ";
            if(DatabaseManager.isOracle()){
                query += " ORDER BY cast(m.text_value as varchar2(128))";
            }else{
                query += " ORDER BY m.text_value";
            }

            tri = DatabaseManager.query(
                    ourContext,
                    query,
                    Constants.COMMUNITY,
                    MetadataField.findByElement(ourContext, MetadataSchema.find(ourContext, MetadataSchema.DC_SCHEMA).getSchemaID(), "title", null).getFieldID(),
                    getID()
            );
        } catch (SQLException e) {
            log.error("Find all Sub Communities - ",e);
            throw e;
        }


        // Make Community objects
        try
        {
            while (tri.hasNext())
            {
                TableRow row = tri.next(ourContext);

                // First check the cache
                Community fromCache = (Community) ourContext.fromCache(
                        Community.class, row.getIntColumn("community_id"));

                if (fromCache != null)
                {
                    subcommunities.add(fromCache);
                }
                else
                {
                    subcommunities.add(new Community(ourContext, row));
                }
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

        // Put them in an array
        Community[] communityArray = new Community[subcommunities.size()];
        communityArray = (Community[]) subcommunities.toArray(communityArray);

        return communityArray;
    }