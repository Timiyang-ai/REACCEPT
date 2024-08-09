@Test
    public void testPCA() {
        INDArray m = Nd4j.randn(10000, 6);
        m.getColumn(0).muli(4.84);
        m.getColumn(1).muli(4.84);
        m.getColumn(2).muli(4.09);
        m.getColumn(1).addi(m.getColumn(2).div(2.0));
        m.getColumn(2).addi(34.286);
        m.getColumn(1).addi(m.getColumn(4));
        m.getColumn(4).subi(m.getColumn(5).div(2.0));
        m.getColumn(5).addi(3.4);
        PCA myPCA = new PCA(m);
        INDArray reduced70 = myPCA.reducedBasis(0.7);
        INDArray reduced99 = myPCA.reducedBasis(0.99);
        assertTrue("Major variance differences should change number of basis vector", reduced99.columns() > reduced70.columns());
        INDArray reduced100 = myPCA.reducedBasis(1.0);
        assertTrue("100% variance coverage should include all eigenvectors", reduced100.columns() == m.columns());
        NDArrayStrings ns = new NDArrayStrings(5);
        System.out.println(ns.format(myPCA.getEigenvectors()));
    }