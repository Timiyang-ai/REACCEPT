@Test
    public void testCompute1() {
        setupParameters();
        initSP();
        
        SparseObjectMatrix<List<Synapse>> s = mem.getPotentialPools();
        
        System.out.println(s);
    }