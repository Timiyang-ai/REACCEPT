private static Git cloneRepository(Project project, String workingTreePath) throws GitAPIException, IOException {
        return Git.cloneRepository()
                .setURI(GitRepository.getGitDirectoryURL(project))
                .setDirectory(new File(workingTreePath))
                .call();
    }