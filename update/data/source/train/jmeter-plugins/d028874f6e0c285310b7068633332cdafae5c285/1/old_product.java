public void add(long xVal, double yVal)
   {
      if (xVal > maxX)
      {
         maxX = xVal;
      }
      if (yVal > maxY)
      {
         maxY = yVal;
      }
      if (xVal < minX)
      {
         minX = xVal;
      }
      if (yVal < minY)
      {
         minY = yVal;
      }
   }