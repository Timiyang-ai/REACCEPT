@Test
    public void testAAT() {
        System.out.println("AAT");
        SparseMatrix c = SparseMatrix.AAT(sm, sm.transpose());
        assertEquals(c.nrows(), 3);
        assertEquals(c.ncols(), 3);
        assertEquals(c.size(), 9);
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                assertEquals(C[i][j], c.get(i, j), 1E-7);
            }
        }
    }