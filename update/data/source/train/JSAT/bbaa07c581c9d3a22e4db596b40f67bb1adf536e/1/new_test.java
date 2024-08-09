@Test
    public void testMultiply_Vec_Double_Vec()
    {
        DenseVector b = new DenseVector(Arrays.asList(4.0, 5.0, 2.0, 6.0, 7.0));
        
        DenseVector z = new DenseVector(Arrays.asList(2.0, 1.0, 2.0, 3.0, 4.0, 5.0, 0.0));
                
        DenseVector store = b.deepCopy();
        
        A.multiply(b, 3.0, store);
        assertEquals(new DenseVector(new double[]{ 448, 335, 311, 288, 454}), store);
        
        DenseVector Cz = new DenseVector(Arrays.asList(62.0, 100.0, 88.0, 74.0, 68.0));
        
        store.zeroOut();
        C.multiply(z, 1.0, store);
        assertEquals(Cz, store);
    }