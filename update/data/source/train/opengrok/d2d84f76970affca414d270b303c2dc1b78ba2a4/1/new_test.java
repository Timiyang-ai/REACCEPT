@Test
    public void testGetProject() {
        // Create 2 projects, one being prefix of the other.
        Project foo = new Project();
        foo.setPath("/foo");
        foo.setDescription("Project foo");

        Project bar = new Project();
        bar.setPath("/foo-bar");
        bar.setDescription("Project foo-bar");

        // Make the runtime environment aware of these two projects.
        List<Project> projects = new ArrayList<>();
        projects.add(foo);
        projects.add(bar);
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        env.setProjects(projects);

        // The matching of project name to project should be exact.
        assertEquals(foo, Project.getProject("/foo"));
        assertEquals(bar, Project.getProject("/foo-bar"));
        assertEquals(foo, Project.getProject("/foo/blah.c"));
        assertEquals(bar, Project.getProject("/foo-bar/ha.c"));
        assertNull(Project.getProject("/foof"));
        assertNull(Project.getProject("/foof/ha.c"));

        // Make sure that the matching is not dependent on list ordering.
        Collections.reverse(projects);
        assertEquals(foo, Project.getProject("/foo"));
        assertEquals(bar, Project.getProject("/foo-bar"));
        assertEquals(foo, Project.getProject("/foo/blah.c"));
        assertEquals(bar, Project.getProject("/foo-bar/ha.c"));
        assertNull(Project.getProject("/foof"));
        assertNull(Project.getProject("/foof/ha.c"));
    }