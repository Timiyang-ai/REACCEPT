    @Test
    public void groupedThreads() {
        ThreadFactory f = Tools.groupedThreads("foo/bar-me", "foo-%d");
        Thread t = f.newThread(() -> TestTools.print("yo"));
        assertTrue("wrong pattern", t.getName().startsWith("foo-bar-me-foo-"));
        assertTrue("wrong group", "foo/bar-me".equals(t.getThreadGroup().getName()));
    }