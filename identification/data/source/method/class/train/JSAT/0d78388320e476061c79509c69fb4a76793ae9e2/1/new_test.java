@Test
    public void testSetCovariance()
    {
        System.out.println("setCovariance");
        NormalM normalM = new NormalM(mean, covariance);
        
        Matrix t1 = Matrix.eye(3);//Should fail, too big
        Matrix t2 = new DenseMatrix(2, 3);//Should fail, not square
        Matrix t3 = new DenseMatrix(3, 2);//Should fail, not square
        
        Matrix[] shouldFail = new Matrix[] {t1, t2, t3};

        for (Matrix badMatrix : shouldFail)
            try
            {
                normalM.setCovariance(badMatrix);
                fail("Matrix was invalid, should have caused an exception");
            }
            catch (ArithmeticException ex)
            {
                //Good! Should fail
            }

    }