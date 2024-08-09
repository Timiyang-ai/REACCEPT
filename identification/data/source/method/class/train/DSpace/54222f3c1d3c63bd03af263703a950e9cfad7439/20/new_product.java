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
                dispatchEvents();
            }
        }
        finally
        {
            if(dbConnection != null)
            {
                //Commit our changes
                dbConnection.commit();
                // Free the DB connection
                dbConnection.closeDBConnection();
                dbConnection = null;
            }
        }
    }