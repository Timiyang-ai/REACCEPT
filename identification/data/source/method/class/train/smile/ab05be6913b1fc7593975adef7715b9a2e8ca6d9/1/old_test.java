@Test
    public void testEM() {
        System.out.println("EM");

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

        Vector<Mixture.Component> m = new Vector<Mixture.Component>();
        Mixture.Component c = new Mixture.Component();
        c.priori = 0.25;
        c.distribution = new GaussianDistribution(0.0, 1.0);
        m.add(c);

        c = new Mixture.Component();
        c.priori = 0.25;
        c.distribution = new ExponentialDistribution(1.0);
        m.add(c);

        c = new Mixture.Component();
        c.priori = 0.5;
        c.distribution = new GammaDistribution(1.0, 2.0);
        m.add(c);

        ExponentialFamilyMixture mixture = new ExponentialFamilyMixture(m, data);
        System.out.println(mixture);
    }