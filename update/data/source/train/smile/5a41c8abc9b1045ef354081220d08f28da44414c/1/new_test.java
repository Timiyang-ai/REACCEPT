@Test
    public void testSolve() {
        System.out.println("solve a vector");
        JMatrix a = new JMatrix(A);
        LU result = a.lu();
        result.solve(b);
        for (int i = 0; i < x.length; i++) {
            assertEquals(x[i], b[i], 1E-7);
        }
    }