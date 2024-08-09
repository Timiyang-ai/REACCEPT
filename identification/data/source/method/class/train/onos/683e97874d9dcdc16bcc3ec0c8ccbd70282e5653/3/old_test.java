    @Test
    public void namedThreads() {
        ThreadFactory f = Tools.namedThreads("foo-%d");
        Thread t = f.newThread(() -> TestTools.print("yo"));
        assertTrue("wrong pattern", t.getName().startsWith("foo-"));
    }