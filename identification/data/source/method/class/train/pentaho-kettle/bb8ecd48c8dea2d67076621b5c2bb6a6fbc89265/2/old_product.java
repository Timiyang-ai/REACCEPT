@SuppressWarnings("deprecation")
    public static Object DateDiff(ValueMetaInterface metaA, Object dataA, ValueMetaInterface metaB, Object dataB) throws KettleValueException
    {
        if (metaA.isDate() && metaB.isDate())
        {
          if (dataA!=null && dataB!=null)
          {
            Date startDate = metaB.getDate(dataB);
            Date endDate = metaA.getDate(dataA);
            // Explicitly zero-out hour/minute/second - not figured in subtraction to determine number of days
            Calendar stDateCal = new GregorianCalendar(startDate.getYear()+1900, startDate.getMonth(), startDate.getDate(), 0, 0, 0);
            // Explicitly zero-out hour/minute/second - not figured in subtraction to determine number of days
            Calendar endDateCal = new GregorianCalendar(endDate.getYear()+1900, endDate.getMonth(), endDate.getDate(), 0, 0, 0);
            // the format creates a number like 2010076 (2010 is the year, 076 is the 76th day of the year).
            int startJulian = (stDateCal.get(Calendar.YEAR) * 1000) + stDateCal.get(Calendar.DAY_OF_YEAR);
            int endJulian = (endDateCal.get(Calendar.YEAR) * 1000) + endDateCal.get(Calendar.DAY_OF_YEAR);
            // I have no idea why this returns a Long, but I'll keep it the same
            return new Long(endJulian - startJulian);
          } else {
            return null;
          }
        }

        throw new KettleValueException("The 'DateDiff' function only works with dates");
    }