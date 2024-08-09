public void abort()
    {
        // If Context is no longer open/valid, just note that it has already been closed
        if(!isValid())
            log.info("abort() was called on a closed Context object. No changes to abort.");

        try
        {
            // Rollback if we have a database connection, and it is NOT Read Only
            if (isValid() && !connection.isClosed() && !isReadOnly())
            {
                connection.rollback();
            }
        }
        catch (SQLException se)
        {
            log.error(se.getMessage(), se);
        }
        finally
        {
            try
            {
                // Free the DB connection
                // If connection is closed or null, this is a no-op
                DatabaseManager.freeConnection(connection);
            }
            catch (Exception ex)
            {
                log.error("Exception aborting context", ex);
            }
            connection = null;
            events = null;
            clearCache();
        }
    }