public final List<String> getLastLogs(int count)
    {
        if (count > logList.size())
        {
            count = logList.size();
        }
        if (logList.size() == 0)
        {
            return new ArrayList<>(0);
        }
        else
        {
            final ArrayDeque<String> list = new ArrayDeque<>(count);
            count--;
            int start = logList.size() - 1;
            int end = start - count;
            for (int i = start; i >= end; i--)
            {

                list.push(logList.get(i));
            }
            return new ArrayList<>(list);
        }
    }