public static OnLineStatistics add(OnLineStatistics A, OnLineStatistics B)
   {
       if(A.n == B.n && B.n == 0)
           return new OnLineStatistics();
       else if(B.n == 0)
           return new OnLineStatistics(A.n, A.mean, A.m2, A.m3, A.m4, A.min, A.max);
       else if(A.n == 0)
           return new OnLineStatistics(B.n, B.mean, B.m2, B.m3, B.m4, B.min, B.max);
       
       int nX = B.n + A.n;
       int nXsqrd = nX*nX;
       int nAnB = B.n*A.n;
       int AnSqrd = A.n*A.n;
       int BnSqrd = B.n*B.n;
       
       double delta = B.mean - A.mean;
       double deltaSqrd = delta*delta;
       double deltaCbd = deltaSqrd*delta;
       double deltaQad = deltaSqrd*deltaSqrd;
       double newMean = (A.n* A.mean + B.n * B.mean)/(A.n + B.n);
       double newM2 = A.m2 + B.m2 + deltaSqrd / nX *nAnB;
       double newM3 = A.m3 + B.m3 + deltaCbd* nAnB*(A.n - B.n) / nXsqrd + 3 * delta * (A.n * B.m2 - B.n * A.m2)/nX;
       double newM4 = A.m4 + B.m4 
               + deltaQad * (nAnB*(AnSqrd - nAnB + BnSqrd)/(nXsqrd*nX)) 
               + 6 * deltaSqrd*(AnSqrd*B.m2 + BnSqrd*A.m2)/nXsqrd
               + 4 * delta *(A.n*B.m3 - B.n*A.m3)/nX;
       
        return new OnLineStatistics(nX, newMean, newM2, newM3, newM4, Math.min(A.min, B.min), Math.max(A.max, B.max));   
   }