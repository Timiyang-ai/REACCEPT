    @Test
    public void cloneRepository() throws Exception {
        // Given
        String userName = "whiteship";
        String projectName = "testProject";

        Project original = createProject(userName, projectName);
        Project fork = createProject("keesun", projectName);

        support.Files.rm_rf(GitRepository.getGitDirectory(original));
        support.Files.rm_rf(GitRepository.getGitDirectory(fork));

        GitRepository fromRepo = new GitRepository(userName, projectName);
        fromRepo.create();

        // When
        String gitUrl = GitRepository.getGitDirectoryURL(original);
        GitRepository.cloneRepository(gitUrl, fork);

        // Then
        File file = GitRepository.getGitDirectory(fork);
        assertThat(file.exists()).isTrue();
    }