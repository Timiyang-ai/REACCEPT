public static void cloneRepository(Project originalProject, Project forkingProject) throws GitAPIException, IOException {
        String directory = getGitDirectory(forkingProject);
        Git.cloneRepository()
                .setURI(getGitDirectoryURL(originalProject))
                .setDirectory(new File(directory))
                .setCloneAllBranches(true)
                .setBare(true)
                .call();
    }