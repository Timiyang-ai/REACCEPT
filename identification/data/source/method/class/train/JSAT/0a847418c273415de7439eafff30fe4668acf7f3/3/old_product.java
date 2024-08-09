public static void OuterProductUpdate(final Matrix A, final Vec a, final Vec b, final double c, ExecutorService threadpool)
    {
        if(a.length() != A.rows() || b.length() != A.cols())
            throw new ArithmeticException("Matrix dimensions do not agree with outer product");
        
        if (a.isSparse())
        {
            final ModifiableCountDownLatch mcdl = new ModifiableCountDownLatch(1);
            for (final IndexValue iv : a)
            {
                mcdl.countUp();
                threadpool.submit(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        A.updateRow(iv.getIndex(), iv.getValue() * c, b);
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
                        for(int i = threadID; i < a.length(); i+=LogicalCores)
                        {
                            double rowCosnt = c*a.get(i);
                            A.updateRow(i, rowCosnt, b);
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