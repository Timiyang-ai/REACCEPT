@Test
    public void testCdf() {
        System.out.println("cdf");
        MultivariateGaussianDistribution instance = new MultivariateGaussianDistribution(mu, sigma);
        for (int i = 0; i < x.length; i++) {
            assertEquals(cdf[i], instance.cdf(x[i]), 5E-3);
        }
    }