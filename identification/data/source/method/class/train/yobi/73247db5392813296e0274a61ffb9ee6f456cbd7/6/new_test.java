    @Test
    public void getPatch() throws IOException, GitAPIException {
        // given
        String userName = "yobi";
        String projectName = "testProject";
        String wcPath = GitRepository.getRepoPrefix() + userName + "/" + projectName;

        String repoPath = wcPath + "/.git";
        File repoDir = new File(repoPath);
        Repository repo = new RepositoryBuilder().setGitDir(repoDir).build();
        repo.create(false);

        Git git = new Git(repo);
        String testFilePath = wcPath + "/readme.txt";
        BufferedWriter out = new BufferedWriter(new FileWriter(testFilePath));

        // when
        out.write("hello 1");
        out.flush();
        git.add().addFilepattern("readme.txt").call();
        git.commit().setMessage("commit 1").call();

        out.write("hello 2");
        out.flush();
        git.add().addFilepattern("readme.txt").call();
        RevCommit commit = git.commit().setMessage("commit 2").call();

        GitRepository gitRepo = new GitRepository(userName, projectName + "/");
        String patch = gitRepo.getPatch(commit.getId().getName());

        // then
        assertThat(patch).contains("-hello 1");
        assertThat(patch).contains("+hello 1hello 2");
    }