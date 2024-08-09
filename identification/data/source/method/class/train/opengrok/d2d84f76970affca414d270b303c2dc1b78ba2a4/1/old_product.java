public static Project getProject(String path) {
        String lpath = path;

        Comparator<Project> cmp = new Comparator<Project>() {
            @Override
            public int compare(Project p1, Project p2) {
                return p1.getPath().compareTo(p2.getPath());
            }
        };

        if (File.separatorChar != '/') {
            lpath = path.replace(File.separatorChar, '/');
        }

        // Try to match each project path as prefix of the given path.
        // This needs to be done from the longest project to the shortest
        // otherwise we could get project mismatches.
        RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        List<Project> sortedProjects = new ArrayList<> (env.getProjects());
        sortedProjects.sort(cmp);
        Collections.reverse(sortedProjects);
        if (env.hasProjects()) {
            for (Project proj : sortedProjects) {
                if (lpath.indexOf(proj.getPath()) == 0) {
                    return proj;
                }
            }
        }

        return null;
    }