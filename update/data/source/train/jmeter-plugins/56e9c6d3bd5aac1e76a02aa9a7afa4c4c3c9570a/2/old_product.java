public double getCpu() {
        double ret;

        try
        {
            String value = getData("cpu");
            if(value != null) {
                ret = Double.parseDouble(value);
            } else {
                ret = MetricsGetter.AGENT_ERROR;
            }
        } catch (IOException ioex)
        {
            ret = MetricsGetter.AGENT_ERROR;
        }

        return ret;
    }