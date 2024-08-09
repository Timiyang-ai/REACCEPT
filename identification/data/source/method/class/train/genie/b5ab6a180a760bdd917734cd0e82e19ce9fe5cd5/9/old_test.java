@Test
    public void testSetDependencies() {
        Assert.assertNotNull(this.c.getDependencies());
        final Set<String> dependencies = Sets.newHashSet("s3://netflix/jars/myJar.jar");
        this.c.setDependencies(dependencies);
        Assert.assertEquals(dependencies, this.c.getDependencies());

        this.c.setDependencies(null);
        Assert.assertThat(this.c.getDependencies(), Matchers.empty());
    }