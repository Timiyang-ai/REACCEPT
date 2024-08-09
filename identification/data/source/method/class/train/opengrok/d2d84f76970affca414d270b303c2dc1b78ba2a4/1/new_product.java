public static Project getProject(String path) {
        // Try to match each project path as prefix of the given path.
        final RuntimeEnvironment env = RuntimeEnvironment.getInstance();
        if (env.hasProjects()) {
            final String lpath = path.replace(File.separatorChar, '/');
            for (Project p : env.getProjects()) {
                String pp = p.getPath();
                // Check if the project's path is a prefix of the given
                // path. It has to be an exact match, or the project's path
                // must be immediately followed by a separator. "/foo" is
                // a prefix for "/foo" and "/foo/bar", but not for "/foof".
                if (lpath.startsWith(pp) &&
                        (pp.length() == lpath.length() ||
                         lpath.charAt(pp.length()) == '/')) {
                    return p;
                }
            }
        }

        return null;
    }