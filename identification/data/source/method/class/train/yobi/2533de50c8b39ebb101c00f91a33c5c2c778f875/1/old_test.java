    @Test
    public void getMetaDataFromPath() throws Exception {
        // Given
        final String userName = "yobi";
        final String projectName = "mytest";
        final String branchName = "branch";
        final String lightWeightTagName = "tag1";
        final String annotatedTagName = "tag2";
        String wcPath = GitRepository.getRepoPrefix() + userName + "/" + projectName;

        Repository repository = GitRepository.buildGitRepository(userName, projectName + "/");
        repository.create();
        Git git = new Git(repository);
        FileUtils.touch(new File(wcPath + "/hello"));
        FileUtils.touch(new File(wcPath + "/dir/world"));
        git.add().addFilepattern("hello").call();
        git.add().addFilepattern("dir").call();
        git.commit().setAuthor("yobi", "yobi@yobi.io").setMessage("test").call();
        git.branchCreate().setName(branchName).call();
        git.tag().setName(lightWeightTagName).setAnnotated(false).call();
        git.tag().setName(annotatedTagName).setAnnotated(true).setMessage("annotated tag").call();
        repository.close();

        running(support.Helpers.makeTestApplication(), new Runnable() {
            @Override
            public void run() {
                try {
                    // When
                    GitRepository gitRepository = new GitRepository(userName, projectName + "/");
                    ObjectNode notExistBranch = gitRepository.getMetaDataFromPath("not_exist_branch", "");
                    ObjectNode root = gitRepository.getMetaDataFromPath("");
                    ObjectNode dir = gitRepository.getMetaDataFromPath("dir");
                    ObjectNode file = gitRepository.getMetaDataFromPath("hello");
                    ObjectNode branch = gitRepository.getMetaDataFromPath(branchName, "");
                    ObjectNode lightWeightTag = gitRepository.getMetaDataFromPath(lightWeightTagName, "");
                    ObjectNode annotatedTag = gitRepository.getMetaDataFromPath(annotatedTagName, "");

                    // Then
                    assertThat(notExistBranch).isNull();
                    assertThat(root.get("type").textValue()).isEqualTo("folder");
                    assertThat(root.get("data").get("hello").get("type").textValue()).isEqualTo("file");
                    assertThat(root.get("data").get("dir").get("type").textValue()).isEqualTo("folder");
                    assertThat(dir.get("type").textValue()).isEqualTo("folder");
                    assertThat(dir.get("data").get("world").get("type").textValue()).isEqualTo("file");
                    assertThat(file.get("type").textValue()).isEqualTo("file");
                    assertThat(branch.toString()).isEqualTo(root.toString());
                    assertThat(lightWeightTag.toString()).isEqualTo(root.toString());
                    assertThat(annotatedTag.toString()).isEqualTo(root.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }