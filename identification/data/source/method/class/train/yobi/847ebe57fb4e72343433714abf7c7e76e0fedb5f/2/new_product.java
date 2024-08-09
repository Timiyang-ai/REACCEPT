public static void cloneRepository(String gitUrl, Project forkingProject) throws GitAPIException, IOException {
        String directory = getGitDirectory(forkingProject);
        Git.cloneRepository()
                .setURI(gitUrl)
                .setDirectory(new File(directory))
                .setCloneAllBranches(true)
                .setBare(true)
                .call();
    }