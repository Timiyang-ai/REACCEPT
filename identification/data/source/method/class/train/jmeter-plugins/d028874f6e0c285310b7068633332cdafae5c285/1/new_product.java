public void add(long xVal, double yVal)
   {
      if (xVal > maxX)
      {
         maxX = xVal;
      }
      if (xVal < minX)
      {
         minX = xVal;
      }
   }