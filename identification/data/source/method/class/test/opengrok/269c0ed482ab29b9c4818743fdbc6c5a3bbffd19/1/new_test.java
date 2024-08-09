@Test
    public void testGetProjectDescriptions() {
        // Create 2 projects.
        Project foo = new Project("foo", "/foo");
        Project bar = new Project("bar", "/bar");

        // Make the runtime environment aware of these two projects.
        HashMap<String,Project> projects = new HashMap<>();
        projects.put("foo", foo);
        projects.put("bar", bar);
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        env.setProjects(projects);

        List<String> descs = env.getProjectNames();
        assertTrue(descs.contains("foo"));
        assertTrue(descs.contains("bar"));
        assertFalse(descs.contains("foobar"));
        assertEquals(2, descs.size());
    }