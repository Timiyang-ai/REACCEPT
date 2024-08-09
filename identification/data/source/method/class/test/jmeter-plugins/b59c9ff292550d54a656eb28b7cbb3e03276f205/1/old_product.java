public double getCpu()
   {
      double ret;

      String value = getData(CPU);
      if (value != null)
      {
         ret = Double.parseDouble(value);
      }
      else
      {
         ret = AGENT_ERROR;
      }

      return ret;
   }