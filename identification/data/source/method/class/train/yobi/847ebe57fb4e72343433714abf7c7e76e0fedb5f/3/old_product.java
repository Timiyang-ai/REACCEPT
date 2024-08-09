private static void cloneRepository(Project project, String workingTreePath) throws GitAPIException, IOException {
        FileUtil.rm_rf(new File(workingTreePath));
        Git.cloneRepository()
                .setURI(GitRepository.getGitDirectoryURL(project))
                .setDirectory(new File(workingTreePath))
                .call();
    }