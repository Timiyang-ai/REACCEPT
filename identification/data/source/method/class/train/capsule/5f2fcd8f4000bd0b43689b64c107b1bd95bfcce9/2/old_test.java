    @Test
    public void isJavaDir() {
        assertEquals("1.7.0", Capsule.isJavaDir("jre7"));
        assertEquals("1.7.0_45", Capsule.isJavaDir("jdk1.7.0_45"));
        assertEquals("1.7.0_51", Capsule.isJavaDir("jdk1.7.0_51.jdk"));
        assertEquals("1.7.0", Capsule.isJavaDir("1.7.0.jdk"));
        assertEquals("1.8.0", Capsule.isJavaDir("jdk1.8.0.jdk"));
        assertEquals("1.7.0", Capsule.isJavaDir("java-7-openjdk-amd64"));
        assertEquals("1.7.0", Capsule.isJavaDir("java-1.7.0-openjdk-amd64"));
        assertEquals("1.7.0", Capsule.isJavaDir("java-1.7.0-openjdk-1.7.0.79.x86_64"));
        assertEquals("1.8.0", Capsule.isJavaDir("java-8-oracle"));
        assertEquals("1.8.0", Capsule.isJavaDir("jdk-8-oracle"));
        assertEquals("1.8.0", Capsule.isJavaDir("jre-8-oracle"));
        assertEquals("1.8.0", Capsule.isJavaDir("jdk-8-oracle-x64"));
        assertEquals("1.8.0", Capsule.isJavaDir("jdk-1.8.0"));
        assertEquals("1.8.0", Capsule.isJavaDir("jre-1.8.0"));
    }