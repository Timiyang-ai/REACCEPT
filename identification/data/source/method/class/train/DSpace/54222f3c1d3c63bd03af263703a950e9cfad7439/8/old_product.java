public Collection[] getCollections() throws SQLException
    {
        List<Collection> collections = new ArrayList<Collection>();

        // Get the table rows
        TableRowIterator tri = null;
        try {
            String query = "SELECT c.* FROM community2collection c2c, collection c "
                    + "LEFT JOIN metadatavalue m on (m.resource_id = c.collection_id and m.resource_type_id = ? and m.metadata_field_id = ?) "
                    + "WHERE c2c.collection_id=c.collection_id AND c2c.community_id=? ";
            if(DatabaseManager.isOracle()){
                query += " ORDER BY cast(m.text_value as varchar2(128))";
            }else{
                query += " ORDER BY m.text_value";
            }
            tri = DatabaseManager.query(
                    ourContext,
                    query,
                    Constants.COLLECTION,
                    MetadataField.findByElement(ourContext, MetadataSchema.find(ourContext, MetadataSchema.DC_SCHEMA).getSchemaID(), "title", null).getFieldID(),
                    getID()
            );
        } catch (SQLException e) {
            log.error("Find all Collections for this community - ",e);
            throw e;
        }

        // Make Collection objects
        try
        {
            while (tri.hasNext())
            {
                TableRow row = tri.next();

                // First check the cache
                Collection fromCache = (Collection) ourContext.fromCache(
                        Collection.class, row.getIntColumn("collection_id"));

                if (fromCache != null)
                {
                    collections.add(fromCache);
                }
                else
                {
                    collections.add(new Collection(ourContext, row));
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
        Collection[] collectionArray = new Collection[collections.size()];
        collectionArray = (Collection[]) collections.toArray(collectionArray);

        return collectionArray;
    }