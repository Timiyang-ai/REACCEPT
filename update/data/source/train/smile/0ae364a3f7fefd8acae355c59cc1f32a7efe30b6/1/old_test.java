@Test
    public void testIsDiagonal() {
        System.out.println("isDiagonal");
        MultivariateGaussianDistribution instance = new MultivariateGaussianDistribution(mu, 1.0);
        assertEquals(true, instance.isDiagonal());

        instance = new MultivariateGaussianDistribution(mu, sigma[0]);
        assertEquals(true, instance.isDiagonal());

        instance = new MultivariateGaussianDistribution(mu, Matrix.of(sigma));
        assertEquals(false, instance.isDiagonal());
    }