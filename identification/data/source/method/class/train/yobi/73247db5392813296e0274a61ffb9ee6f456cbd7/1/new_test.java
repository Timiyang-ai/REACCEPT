    @Test
    public void getHistory() throws IOException, GitAPIException {
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
        git.commit().setMessage("commit 2").call();

        git.tag().setName("tag").setAnnotated(true).call();

        out.write("hello 3");
        out.flush();
        git.add().addFilepattern("readme.txt").call();
        git.commit().setMessage("commit 3").call();

        GitRepository gitRepo = new GitRepository(userName, projectName + "/");

        List<Commit> history2 = gitRepo.getHistory(0, 2, "HEAD", null);
        List<Commit> history5 = gitRepo.getHistory(0, 5, null, null);
        List<Commit> tagHistory2 = gitRepo.getHistory(0, 2, "tag", null);

        // then
        assertThat(history2.size()).isEqualTo(2);
        assertThat(history2.get(0).getMessage()).isEqualTo("commit 3");
        assertThat(history2.get(1).getMessage()).isEqualTo("commit 2");

        assertThat(history5.size()).isEqualTo(3);
        assertThat(history5.get(0).getMessage()).isEqualTo("commit 3");
        assertThat(history5.get(1).getMessage()).isEqualTo("commit 2");
        assertThat(history5.get(2).getMessage()).isEqualTo("commit 1");

        assertThat(tagHistory2.size()).isEqualTo(2);
        assertThat(tagHistory2.get(0).getMessage()).isEqualTo("commit 2");
        assertThat(tagHistory2.get(1).getMessage()).isEqualTo("commit 1");
    }