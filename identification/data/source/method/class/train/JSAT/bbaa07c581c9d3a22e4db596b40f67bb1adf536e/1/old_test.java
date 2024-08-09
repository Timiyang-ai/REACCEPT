@Test
    public void testMultiply_Vec_ExecutorService()
    {
        DenseVector b = new DenseVector(Arrays.asList(4.0, 5.0, 2.0, 6.0, 7.0));
        
        DenseVector z = new DenseVector(Arrays.asList(2.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0));
        
        DenseVector Ab = new DenseVector(Arrays.asList(148.0, 110.0, 103.0, 94.0, 149.0));
        
        assertEquals(Ab, A.multiply(b, threadpool));
        
        DenseVector Cz = new DenseVector(Arrays.asList(62.0, 100.0, 88.0, 74.0, 68.0));
        
        assertEquals(Cz, C.multiply(z, threadpool));
    }