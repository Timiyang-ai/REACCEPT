    @Test
    public void interpolate() throws Exception {
        MojoParameters params = mockMojoParams();
        Map<String, String> filterMapping = new HashMap<>();
        filterMapping.put("none", "false");
        filterMapping.put("var", "${*}");
        filterMapping.put("at", "@");

        for (Map.Entry<String, String> entry : filterMapping.entrySet()) {
            for (int i = 1; i < 2; i++) {
                File dockerFile = getDockerfilePath(i, entry.getKey());
                File expectedDockerFile = new File(dockerFile.getParent(), dockerFile.getName() + ".expected");
                File actualDockerFile = createTmpFile(dockerFile.getName());
                FixedStringSearchInterpolator interpolator = DockerFileUtil.createInterpolator(params, entry.getValue());
                FileUtils.write(actualDockerFile, DockerFileUtil.interpolate(dockerFile, interpolator), "UTF-8");
                // Compare text lines without regard to EOL delimiters
                assertEquals(FileUtils.readLines(expectedDockerFile), FileUtils.readLines(actualDockerFile));
            }
        }
    }