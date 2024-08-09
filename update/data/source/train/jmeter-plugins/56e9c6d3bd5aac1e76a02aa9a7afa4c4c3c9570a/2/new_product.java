public double getCpu()
   {
      double ret;

      String value = getData("cpu");
      if (value != null)
      {
         ret = Double.parseDouble(value);
      }
      else
      {
         ret = MetricsGetter.AGENT_ERROR;
      }

      return ret;
   }