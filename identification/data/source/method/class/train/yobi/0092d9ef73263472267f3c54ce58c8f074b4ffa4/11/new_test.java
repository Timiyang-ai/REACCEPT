    @Test
    public void cancelEnroll() {
        // Given
        Project project = Project.find.byId(1L);
        User user = User.find.byId(6L);

        // When
        Result result = callAction(
                routes.ref.EnrollProjectApp.cancelEnroll(project.owner, project.name),
                fakeRequest(POST,
                        routes.EnrollProjectApp.cancelEnroll(project.owner, project.name).url())
                        .withSession(UserApp.SESSION_USERID, user.id.toString()));

        // Then
        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(user.enrolledProjects).isEmpty();
    }