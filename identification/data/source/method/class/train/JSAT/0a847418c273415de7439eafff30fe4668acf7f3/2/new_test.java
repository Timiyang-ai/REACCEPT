    @Test
    public void testOuterProductUpdate_4args()
    {
        System.out.println("OuterProductUpdate");
        Matrix A = Matrix.eye(4);
        Vec x = new DenseVector(new double[]{1, 2, 3, 4});
        Vec y = new DenseVector(new double[]{5, 6, 7, 8});
        double c = 2.0;
        
        double[][] expected = new double[][]
        {
            {11, 12, 14, 16},
            {20, 25, 28, 32},
            {30, 36, 43, 48},
            {40, 48, 56, 65},
        };
        
        Matrix.OuterProductUpdate(A, x, y, c);
        
        for(int i = 0; i < expected.length; i++)
            for(int j = 0; j < expected.length; j++)
                assertEquals(expected[i][j], A.get(i, j), 0.0);
    }