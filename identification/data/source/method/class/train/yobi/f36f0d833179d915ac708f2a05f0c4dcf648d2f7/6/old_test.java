    @Test
    public void labels() {
        //Given
        Project project = Project.findByOwnerAndProjectName("yobi", "projectYobi");

        Label label1 = new Label("OS", "yobi-linux");
        label1.save();
        project.labels.add(label1);
        project.update();

        String user = "yobi";
        String projectName = "projectYobi";

        // If null is given as the first parameter, "Label" is chosen as the category.
        Label label2 = new Label(null, "foo");
        label2.save();
        project.labels.add(label2);
        project.update();

        //When
        Result result = callAction(
                controllers.routes.ref.ProjectApp.labels(user, projectName),
                fakeRequest(GET, routes.ProjectApp.labels(user, projectName).url()).withHeader(
                        "Accept", "application/json"));

        //Then
        assertThat(status(result)).isEqualTo(OK);
        JsonNode json = Json.parse(contentAsString(result));

        String id1 = label1.id.toString();
        String id2 = label2.id.toString();

        assertThat(json.has(id1)).isTrue();
        assertThat(json.has(id2)).isTrue();
        assertThat(json.get(id1).get("category").asText()).isEqualTo("OS");
        assertThat(json.get(id1).get("name").asText()).isEqualTo("yobi-linux");
        assertThat(json.get(id2).get("category").asText()).isEqualTo("Label");
        assertThat(json.get(id2).get("name").asText()).isEqualTo("foo");
    }