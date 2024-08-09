@Test
    public void testCompute1() {
        setupParameters();
        initSP();
        
        SparseObjectMatrix<int[]> s = mem.getPotentialPools();
        
        System.out.println(s);
    }