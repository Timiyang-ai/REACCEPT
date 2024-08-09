public static Group findByName(Context context, String name)
        throws SQLException
    {
        TableRow row = DatabaseManager.findByUnique( context, "epersongroup", "name", name );

        if ( row == null )
        {
            return null;
        }
        else
        {
            // First check the cache
            Group fromCache = (Group) context.fromCache(
                Group.class,
                row.getIntColumn("group_id"));

            if (fromCache != null)
            {
                return fromCache;
            }
            else
            {
                return new Group( context, row );
            }
        }
    }