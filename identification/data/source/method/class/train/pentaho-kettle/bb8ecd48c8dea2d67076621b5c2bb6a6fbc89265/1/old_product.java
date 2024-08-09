public static Object DateDiff(ValueMetaInterface metaA, Object dataA, ValueMetaInterface metaB, Object dataB) throws KettleValueException
    {
        if (metaA.isDate() && metaB.isDate())
        {
        	 if (dataA!=null && dataB!=null)
             {
                 // Get msec from each, and subtract.
                 long diff = metaA.getDate(dataA).getTime() - metaB.getDate(dataB).getTime();
                 return new Long(diff / (1000 * 60 * 60 * 24));  
             }else
            	 return null;
        }

        throw new KettleValueException("The 'DateDiff' function only works with dates");
    }