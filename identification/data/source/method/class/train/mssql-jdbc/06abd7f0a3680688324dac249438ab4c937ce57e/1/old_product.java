public boolean execute() throws SQLServerException {
        loggerExternal.entering(getClassNameLogging(), "execute");
        if (loggerExternal.isLoggable(Level.FINER) && Util.IsActivityTraceOn()) {
            loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
        }
        checkClosed();
        executeStatement(new PrepStmtExecCmd(this, EXECUTE));
        loggerExternal.exiting(getClassNameLogging(), "execute", Boolean.valueOf(null != resultSet));
        return null != resultSet;
    }