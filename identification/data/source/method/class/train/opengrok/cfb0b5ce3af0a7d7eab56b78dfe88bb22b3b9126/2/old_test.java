@Test
    public void testGetProjectDescriptions() {
        // Create 2 projects.
        Project foo = new Project();
        foo.setPath("/foo");
        foo.setName("foo");

        Project bar = new Project();
        bar.setPath("/bar");
        bar.setName("bar");

        // Make the runtime environment aware of these two projects.
        List<Project> projects = new ArrayList<>();
        projects.add(foo);
        projects.add(bar);
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        env.setProjects(projects);

        List<String> descs = env.getProjectDescriptions();
        assertTrue(descs.contains("foo"));
        assertTrue(descs.contains("bar"));
        assertFalse(descs.contains("foobar"));
        assertEquals(2, descs.size());
    }