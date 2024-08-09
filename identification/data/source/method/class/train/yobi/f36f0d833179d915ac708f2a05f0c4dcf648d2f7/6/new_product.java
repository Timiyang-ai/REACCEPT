public static Result labels(String owner, String projectName) {
        Project project = Project.findByOwnerAndProjectName(owner, projectName);

        if (project == null) {
            return notFound();
        }

        if (!AccessControl.isAllowed(UserApp.currentUser(), project.asResource(), Operation.READ)) {
            return forbidden();
        }

        if (!request().accepts("application/json")) {
            return status(Http.Status.NOT_ACCEPTABLE);
        }

        Map<Long, Map<String, String>> labels = new HashMap<>();
        for (Label label: project.labels) {
            Map<String, String> tagMap = new HashMap<>();
            tagMap.put("category", label.category);
            tagMap.put("name", label.name);
            labels.put(label.id, tagMap);
        }

        return ok(toJson(labels));
    }