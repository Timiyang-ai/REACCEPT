public double getCpu() throws PerfMonException
   {
      double ret = -1;

      String value = getData(CPU);
      if (value != null) ret = Double.parseDouble(value);
      if(ret < 0) throwNotSupportedMetricException("cpu");

      return ret;
   }