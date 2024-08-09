@Test
    public void testSetJars() {
        Assert.assertNull(this.a.getDependencies());
        final Set<String> jars = new HashSet<>();
        jars.add("s3://netflix/jars/myJar.jar");
        this.a.setDependencies(jars);
        Assert.assertEquals(jars, this.a.getDependencies());
    }