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
            final ArrayList<String> list = new ArrayList<>(count);
            count--;
            for (int i = 0; i < count; i++)
            {
                list.add(logList.get(i));
            }
            return list;
        }
    }