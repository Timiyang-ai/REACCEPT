@Test
    public void testSetDependencies() {
        Assert.assertNotNull(this.c.getDependencies());
        final FileEntity dependency = new FileEntity();
        dependency.setFile("s3://netflix/jars/myJar.jar");
        final Set<FileEntity> dependencies = Sets.newHashSet(dependency);
        this.c.setDependencies(dependencies);
        Assert.assertEquals(dependencies, this.c.getDependencies());

        this.c.setDependencies(null);
        Assert.assertThat(this.c.getDependencies(), Matchers.empty());
    }