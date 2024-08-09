public long getMem()
   {
      long ret;

      String value = getData(MEMORY);
      if (value != null)
      {
         ret = Long.parseLong(value);
      }
      else
      {
         ret = AGENT_ERROR;
      }

      return ret;
   }