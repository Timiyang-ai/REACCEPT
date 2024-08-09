@Test
    public void testDiagonal() {
        System.out.println("diagonal");
        MultivariateGaussianDistribution instance = new MultivariateGaussianDistribution(mu, 1.0);
        assertEquals(true, instance.diagonal);

        instance = new MultivariateGaussianDistribution(mu, sigma[0]);
        assertEquals(true, instance.diagonal);

        instance = new MultivariateGaussianDistribution(mu, Matrix.of(sigma));
        assertEquals(false, instance.diagonal);
    }