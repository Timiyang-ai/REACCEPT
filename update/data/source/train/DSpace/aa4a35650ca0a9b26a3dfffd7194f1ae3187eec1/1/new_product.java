public static List<MetadataValue> findByField(Context context, int fieldId)
            throws IOException, SQLException, AuthorizeException
    {
        // Grab rows from DB
        TableRowIterator tri = DatabaseManager.queryTable(context, "MetadataValue",
                "SELECT * FROM MetadataValue WHERE metadata_field_id= ? ",
                fieldId);

        TableRow row = null;
        List<MetadataValue> ret = new ArrayList<MetadataValue>();
        try
        {
            while (tri.hasNext())
            {
                row = tri.next();
                ret.add(new MetadataValue(row));
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

        return ret;
    }