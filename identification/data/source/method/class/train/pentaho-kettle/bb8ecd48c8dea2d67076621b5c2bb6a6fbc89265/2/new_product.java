public static Object DateDiff(ValueMetaInterface metaA, Object dataA, ValueMetaInterface metaB, Object dataB) throws KettleValueException
    {
        if (metaA.isDate() && metaB.isDate())
        {
          if (dataA!=null && dataB!=null)
          {
            Date startDate = metaB.getDate(dataB);
            Date endDate = metaA.getDate(dataA);

			Calendar stDateCal = Calendar.getInstance();
			Calendar endDateCal = Calendar.getInstance();
			stDateCal.setTime(startDate);
			endDateCal.setTime(endDate);

			long endL = endDateCal.getTimeInMillis() + endDateCal.getTimeZone().getOffset( endDateCal.getTimeInMillis() );
			long startL = stDateCal.getTimeInMillis() + stDateCal.getTimeZone().getOffset( stDateCal.getTimeInMillis() );

			return new Long(((endL - startL) / 86400000));
          } else {
            return null;
          }
        }

        throw new KettleValueException("The 'DateDiff' function only works with dates");
    }