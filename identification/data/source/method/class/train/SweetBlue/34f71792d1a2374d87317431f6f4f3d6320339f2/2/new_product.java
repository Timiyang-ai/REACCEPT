public final List<String> getLastLogs(int count)
    {
        if (count > m_logList.size())
        {
            count = m_logList.size();
        }
        if (m_logList.size() == 0)
        {
            return new ArrayList<>(0);
        }
        else
        {
            final ArrayDeque<String> list = new ArrayDeque<>(count);
            count--;
            int start = m_logList.size() - 1;
            int end = start - count;
            for (int i = start; i >= end; i--)
            {

                list.push(m_logList.get(i));
            }
            return new ArrayList<>(list);
        }
    }