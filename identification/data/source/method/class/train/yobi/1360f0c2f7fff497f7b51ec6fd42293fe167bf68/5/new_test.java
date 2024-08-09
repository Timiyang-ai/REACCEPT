    @Test
    public void newProject() throws Exception {
        // Given
        formData.put("url", GitRepository.getGitDirectoryURL(src));
        formData.put("owner", yobi.loginId);
        formData.put("name", dest.name);
        formData.put("projectScope", "PUBLIC");
        formData.put("vcs", "GIT");

        // When
        Result result = callAction(ref.ImportApp.newProject(),
                fakeRequest()
                    .withSession(UserApp.SESSION_USERID, String.valueOf(yobi.id))
                    .withFormUrlEncodedBody(formData));

        // Then
        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(header(LOCATION, result)).isEqualTo(routes.ProjectApp.project(dest.owner, dest.name).url());
        assertThat(Project.findByOwnerAndProjectName(dest.owner, dest.name)).isNotNull();
        assertThat(new File(GitRepository.getGitDirectoryURL(dest)).exists()).isTrue();
    }