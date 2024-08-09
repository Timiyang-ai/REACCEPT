    @Test
    public void detachLabel() {
        //Given
        Project project = Project.findByOwnerAndProjectName("yobi", "projectYobi");

        Label label1 = new Label("OS", "linux");
        label1.save();
        project.labels.add(label1);
        project.update();
        Long labelId = label1.id;

        assertThat(project.labels.contains(label1)).isTrue();

        Map<String,String> data = new HashMap<>();
        data.put("_method", "DELETE");
        User admin = User.findByLoginId("admin");

        String user = "yobi";
        String projectName = "projectYobi";

        //When
        Result result = callAction(
                controllers.routes.ref.ProjectApp.detachLabel(user, projectName, labelId),
                fakeRequest(POST, routes.ProjectApp.detachLabel(user, projectName, labelId).url())
                        .withFormUrlEncodedBody(data).withHeader("Accept", "application/json")
                        .withSession(UserApp.SESSION_USERID, admin.id.toString()));

        //Then
        assertThat(status(result)).isEqualTo(204);
        assertThat(Project.findByOwnerAndProjectName("yobi", "projectYobi").labels.contains(label1)).isFalse();
    }