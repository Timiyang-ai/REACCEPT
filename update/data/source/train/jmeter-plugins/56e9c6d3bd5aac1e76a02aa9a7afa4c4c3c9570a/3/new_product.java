public long getMem()
   {
      long ret;

      String value = getData("mem");
      if (value != null)
      {
         ret = Long.parseLong(value);
      }
      else
      {
         ret = MetricsGetter.AGENT_ERROR;
      }

      return ret;
   }