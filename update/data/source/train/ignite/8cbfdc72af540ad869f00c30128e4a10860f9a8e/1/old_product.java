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

        cliIo.close();

        timer.cancel();

        if (err != null)
            throw err;
    }