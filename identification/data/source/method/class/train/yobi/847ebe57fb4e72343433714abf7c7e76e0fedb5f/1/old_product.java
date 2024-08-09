private static void cloneRepository(Project project, String workingTreePath) throws GitAPIException, IOException {
        Git.cloneRepository()
                .setURI(GitRepository.getGitDirectoryURL(project))
                .setDirectory(new File(workingTreePath))
                .call();
    }