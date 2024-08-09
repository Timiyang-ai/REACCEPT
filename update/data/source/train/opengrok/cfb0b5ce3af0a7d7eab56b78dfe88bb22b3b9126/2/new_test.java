@Test
    public void testGetProject() {
        // Create 2 projects, one being prefix of the other.
        Project foo = new Project("Project foo", "/foo");
        Project bar = new Project("Project foo-bar", "/foo-bar");

        // Make the runtime environment aware of these two projects.
        HashMap<String,Project> projects = new HashMap<>();
        projects.put("foo", foo);
        projects.put("bar", bar);
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        env.setProjects(projects);

        // The matching of project name to project should be exact.
        assertEquals(foo, Project.getProject("/foo"));
        assertEquals(bar, Project.getProject("/foo-bar"));
        assertEquals(foo, Project.getProject("/foo/blah.c"));
        assertEquals(bar, Project.getProject("/foo-bar/ha.c"));
        assertNull(Project.getProject("/foof"));
        assertNull(Project.getProject("/foof/ha.c"));
    }