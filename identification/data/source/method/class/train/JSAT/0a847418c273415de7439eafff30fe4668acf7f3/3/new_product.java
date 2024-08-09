public static void OuterProductUpdate(final Matrix A, final Vec x, final Vec y, final double c, ExecutorService threadpool)
    {
        if(x.length() != A.rows() || y.length() != A.cols())
            throw new ArithmeticException("Matrix dimensions do not agree with outer product");
        
        if (x.isSparse())
        {
            final ModifiableCountDownLatch mcdl = new ModifiableCountDownLatch(1);
            for (final IndexValue iv : x)
            {
                mcdl.countUp();
                threadpool.submit(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        A.updateRow(iv.getIndex(), iv.getValue() * c, y);
                        mcdl.countDown();
                    }
                });
            }
            mcdl.countDown();
            try
            {
                mcdl.await();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            final CountDownLatch latch = new CountDownLatch(LogicalCores);
            for(int id = 0; id < LogicalCores; id++)
            {
                final int threadID = id;
                threadpool.submit(new Runnable() 
                {

                    @Override
                    public void run()
                    {
                        for(int i = threadID; i < x.length(); i+=LogicalCores)
                        {
                            double rowCosnt = c*x.get(i);
                            A.updateRow(i, rowCosnt, y);
                        }
                        latch.countDown();
                    }
                });

            }
            try
            {
                latch.await();
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Matrix.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }