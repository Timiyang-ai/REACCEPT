@Test
    public void testPCA() {
        INDArray m = Nd4j.randn(10000, 16);
        // random correlated samples of 16 features to analyze
        m.getColumn(0).muli(4.84);
        m.getColumn(1).muli(4.84);
        m.getColumn(2).muli(4.09);
        m.getColumn(1).addi(m.getColumn(2).div(2.0));
        m.getColumn(2).addi(34.286);
        m.getColumn(1).addi(m.getColumn(4));
        m.getColumn(4).subi(m.getColumn(5).div(2.0));
        m.getColumn(5).addi(3.4);
        m.getColumn(6).muli(6.0);
        m.getColumn(7).muli(0.2);
        m.getColumn(8).muli(2.0);
        m.getColumn(9).muli(6.0);
        m.getColumn(9).addi(m.getColumn(6).mul(1.0));
        m.getColumn(10).muli(0.2);
        m.getColumn(11).muli(2.0);
        m.getColumn(12).muli(0.2);
        m.getColumn(13).muli(4.0);
        m.getColumn(14).muli(3.2);
        m.getColumn(14).addi(m.getColumn(2).mul(1.0)).subi(m.getColumn(13).div(2.0));
        m.getColumn(15).muli(1.0);
        m.getColumn(13).subi(12.0);
        m.getColumn(15).addi(30.0);
        PCA myPCA = new PCA(m);
        INDArray reduced70 = myPCA.reducedBasis(0.70);
        INDArray reduced99 = myPCA.reducedBasis(0.99);
        assertTrue("Major variance differences should change number of basis vectors", reduced99.columns() > reduced70.columns());
        INDArray reduced100 = myPCA.reducedBasis(1.0);
        assertTrue("100% variance coverage should include all eigenvectors", reduced100.columns() == m.columns());
        NDArrayStrings ns = new NDArrayStrings(5);
        System.out.println("Eigenvectors:\n" + ns.format(myPCA.getEigenvectors()));
        System.out.println("Eigenvalues:\n" + ns.format(myPCA.getEigenvalues()));
        double variance = 0.0;
        // sample 1000 of the randomly generated samples with the reduced basis set
        for (int i = 0; i < 1000; i++)
            variance += myPCA.estimateVariance(m.getRow(i), reduced70.columns());
        variance /= 1000.0;
        System.out.println("Fraction of variance using 70% variance with " + reduced70.columns() + " columns: " + variance);
        assertTrue("Variance does not cover intended 70% variance", variance > 0.70);
        // create "dummy" data with the same exact trends
        INDArray testSample = myPCA.generateGaussianSamples(10000);
        PCA analyzePCA = new PCA(testSample);
        for (int i = 0; i < testSample.columns(); i++) {
            assertEquals(myPCA.getMean().getDouble(i), analyzePCA.getMean().getDouble(i), 0.3);
            assertEquals(myPCA.getCovarianceMatrix().getDouble(i,i),
                    analyzePCA.getCovarianceMatrix().getDouble(i,i), 3.0);
            assertEquals(myPCA.getEigenvalues().getDouble(i), analyzePCA.getEigenvalues().getDouble(i), 1.0);
        }
        System.out.println("Original cov:\n"+ns.format(myPCA.getCovarianceMatrix()) + "\nDummy cov:\n" + ns.format(analyzePCA.getCovarianceMatrix()));
        INDArray testSample2 = analyzePCA.convertBackToFeatures(analyzePCA.convertToComponents(testSample));
        assertTrue("Transformation does not work.", testSample.equalsWithEps(testSample2, 1e-4*testSample.rows()*testSample.columns()));
    }