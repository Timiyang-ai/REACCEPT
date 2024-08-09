@Test
    public void testClone() throws CloneNotSupportedException {
        MixtureComponent gaussian = new MixtureComponent(lm, new float[]{2}, new float[][]{{3}}, new float[]{4}, new float[]{5}, new float[][]{{6}}, new float[]{7});

        MixtureComponent clonedGaussian = (MixtureComponent) gaussian.clone();

        assertTrue(!clonedGaussian.equals(gaussian));

        assertTrue(gaussian.getMean() != clonedGaussian.getMean());
        assertTrue(gaussian.getVariance() != clonedGaussian.getVariance());
        assertTrue(gaussian.getScore(new float[]{2}) == clonedGaussian.getScore(new float[]{2}));
    }