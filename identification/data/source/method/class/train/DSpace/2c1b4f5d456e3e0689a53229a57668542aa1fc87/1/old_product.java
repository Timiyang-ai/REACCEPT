public static Group findByName(Context context, String name)
        throws SQLException, AuthorizeException
    {
        TableRow row = DatabaseManager.findByUnique( context, "epersongroup", "name", name );

        if ( row == null )
        {
            return null;
        }
        else
        {
            return new Group( context, row );
        }
    }