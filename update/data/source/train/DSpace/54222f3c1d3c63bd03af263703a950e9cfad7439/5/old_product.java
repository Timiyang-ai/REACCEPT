public void complete() throws SQLException
    {
        // If Context is no longer open/valid, just note that it has already been closed
        if(!isValid())
            log.info("complete() was called on a closed Context object. No changes to commit.");

        // FIXME: Might be good not to do a commit() if nothing has actually
        // been written using this connection
        try
        {
            // As long as we have a valid, writeable database connection,
            // commit any changes made as part of the transaction
            if (isValid() && !isReadOnly())
            {
                commit();
            }
        }
        finally
        {
            // Free the DB connection
            // If connection is closed or null, this is a no-op
            DatabaseManager.freeConnection(connection);
            connection = null;
            clearCache();
        }
    }