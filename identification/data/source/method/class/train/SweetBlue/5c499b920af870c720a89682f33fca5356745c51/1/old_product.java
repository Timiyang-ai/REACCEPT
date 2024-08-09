public final String getLastLog()
    {
        if (logList.size() > 0)
        {
            return logList.get(logList.size() - 1);
        }
        return "";
    }