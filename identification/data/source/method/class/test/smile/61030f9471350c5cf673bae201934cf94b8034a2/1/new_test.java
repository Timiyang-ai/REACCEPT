@Test
    public void testEM() {
        System.out.println("EM");
        MathEx.setSeed(19650218); // to get repeatable results.

        // Mixture of Gaussian, Exponential, and Gamma.
        double[] data = new double[2000];

        GaussianDistribution gaussian = new GaussianDistribution(-2.0, 1.0);
        for (int i = 0; i < 500; i++)
            data[i] = gaussian.rand();

        ExponentialDistribution exponential = new ExponentialDistribution(0.8);
        for (int i = 500; i < 1000; i++)
            data[i] = exponential.rand();

        GammaDistribution gamma = new GammaDistribution(2.0, 3.0);
        for (int i = 1000; i < 2000; i++)
            data[i] = gamma.rand();

        ExponentialFamilyMixture mixture = ExponentialFamilyMixture.fit(data,
                new Mixture.Component(0.25, new GaussianDistribution(0.0, 1.0)),
                new Mixture.Component(0.25, new ExponentialDistribution(1.0)),
                new Mixture.Component(0.5, new GammaDistribution(1.0, 2.0))
                );
        System.out.println(mixture);
    }