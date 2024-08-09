@Override public void close() throws SQLException {
        if (isClosed())
            return;

        closed = true;

        maintenanceExecutor.shutdown();

        if (streamState != null) {
            streamState.close();

            streamState = null;
        }

        synchronized (stmtsMux) {
            stmts.clear();
        }

        SQLException err = null;

        if (partitionAwareness) {
            for (JdbcThinTcpIo clioIo : ios.values())
                clioIo.close();

            ios.clear();
        }
        else {
            if (singleIo != null)
                singleIo.close();
        }

        if (err != null)
            throw err;
    }