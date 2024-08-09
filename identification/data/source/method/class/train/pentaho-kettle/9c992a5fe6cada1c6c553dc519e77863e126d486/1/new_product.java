public static final DatabaseMeta findDatabase(List<DatabaseMeta> databases, long id)
    {
        if (databases == null)
            return null;

        for (DatabaseMeta ci : databases)
        {
            if (ci.getID() == id) {
                return ci;
            }
        }
        return null;
    }