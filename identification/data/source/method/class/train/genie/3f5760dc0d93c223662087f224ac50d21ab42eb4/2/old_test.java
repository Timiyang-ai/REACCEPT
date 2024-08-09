@Test
    public void testSetJars() {
        Assert.assertNull(this.a.getJars());
        final Set<String> jars = new HashSet<>();
        jars.add("s3://netflix/jars/myJar.jar");
        this.a.setJars(jars);
        Assert.assertEquals(jars, this.a.getJars());
    }