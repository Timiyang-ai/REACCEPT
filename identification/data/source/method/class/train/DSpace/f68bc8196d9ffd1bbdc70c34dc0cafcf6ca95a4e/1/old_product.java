public static Group[] findAll(Context context, int sortField)
        throws SQLException
    {
        String s;
        
        switch (sortField)
        {
            case ID:
                s = "eperson_group_id";
                break;
                
            case NAME:
                s = "name";
                break;
                
            default:
                s = "name";
        }
        
        TableRowIterator rows = DatabaseManager.query(context,
            "SELECT * FROM epersongroup ORDER BY " + s);

        List gRows = rows.toList();
        
        Group[] groups = new Group[gRows.size()];
        
        for (int i = 0; i < gRows.size(); i++)
        {
            TableRow row = (TableRow) gRows.get(i);

            // First check the cache
            Group fromCache = (Group) context.fromCache(
                Group.class,
                row.getIntColumn("eperson_group_id"));

            if (fromCache != null)
            {
                groups[i] = fromCache;
            }
            else
            {
                groups[i] = new Group(context, row);
            }
        }

        return groups;
    }