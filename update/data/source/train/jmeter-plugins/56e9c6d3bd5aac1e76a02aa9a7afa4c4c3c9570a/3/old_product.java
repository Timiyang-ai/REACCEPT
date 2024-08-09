public long getMem() {
        long ret;

        try
        {
            String value = getData("mem");
            if(value != null) {
                ret = Long.parseLong(value);
            } else {
                ret = MetricsGetter.AGENT_ERROR;
            }
        } catch (IOException ioex)
        {
            ret = MetricsGetter.AGENT_ERROR;
        }

        return ret;
    }