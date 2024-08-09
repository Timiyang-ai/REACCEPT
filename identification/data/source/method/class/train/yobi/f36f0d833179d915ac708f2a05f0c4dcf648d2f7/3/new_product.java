@IsAllowed(Operation.READ)
    public static Result labels(String ownerId, String projectName) {
        if (!request().accepts("application/json")) {
            return status(Http.Status.NOT_ACCEPTABLE);
        }

        Project project = Project.findByOwnerAndProjectName(ownerId, projectName);

        Map<Long, Map<String, String>> labels = new HashMap<>();
        for (Label label: project.labels) {
            labels.put(label.id, convertToMap(label));
        }

        return ok(toJson(labels));
    }