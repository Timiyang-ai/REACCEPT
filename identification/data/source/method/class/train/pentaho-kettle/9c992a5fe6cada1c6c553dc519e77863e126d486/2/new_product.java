public static final DatabaseMeta findDatabase(List<? extends SharedObjectInterface> databases, String dbname)
    {
        if (databases == null)
            return null;

        for (int i = 0; i < databases.size(); i++)
        {
            DatabaseMeta ci = (DatabaseMeta) databases.get(i);
            if (ci.getName().equalsIgnoreCase(dbname))
                return ci;
        }
        return null;
    }