    @Test
    public void deletePushedBranch() {
        //Given
        String loginId = "yobi";
        String projectName = "projectYobi";
        Project project = Project.findByOwnerAndProjectName(loginId, projectName);
        PushedBranch pushedBranch = new PushedBranch(new Date(), "testBranch", project);
        pushedBranch.save();
        Long id = pushedBranch.id;

        //When
        Result result = callAction(
                controllers.routes.ref.ProjectApp.deletePushedBranch(project.owner, project.name, id),
                fakeRequest(DELETE, "/yobi/projectYobi/deletePushedBranch/" + id)
                        .withSession(UserApp.SESSION_USERID, User.findByLoginId(loginId).id.toString())
                );

        //Then
        assertThat(status(result)).isEqualTo(OK);
        assertThat(PushedBranch.find.byId(id)).isNull();

    }