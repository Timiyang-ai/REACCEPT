public final String getLastLog()
    {
        if (m_logList.size() > 0)
        {
            return m_logList.get(m_logList.size() - 1);
        }
        return "";
    }