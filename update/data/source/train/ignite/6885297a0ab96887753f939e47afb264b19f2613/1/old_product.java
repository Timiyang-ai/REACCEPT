@Override public void close() throws SQLException {
        if (isClosed())
            return;

        if (streamState != null) {
            streamState.close();

            streamState = null;
        }

        synchronized (stmtsMux) {
            stmts.clear();
        }

        SQLException err = null;

        closed = true;

        if (bestEffortAffinity) {
            for (JdbcThinTcpIo clioIo : ios.values())
                clioIo.close();

            ios.clear();

            iosArr = null;
        }
        else {
            if (singleIo != null)
                singleIo.close();
        }

        timer.cancel();

        if (err != null)
            throw err;
    }