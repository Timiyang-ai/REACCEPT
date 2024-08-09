public long getMem() throws PerfMonException
   {
      long ret = -1;

      String value = getData(MEMORY);
      if (value != null) ret = Long.parseLong(value);
      if(ret <= 0) throwNotSupportedMetricException("memory");

      return ret;
   }