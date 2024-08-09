public static final DatabaseMeta findDatabase(List databases, long id)
    {
        if (databases == null)
            return null;

        for (int i = 0; i < databases.size(); i++)
        {
            DatabaseMeta ci = (DatabaseMeta) databases.get(i);
            if (ci.getID() == id)
                return ci;
        }
        return null;
    }