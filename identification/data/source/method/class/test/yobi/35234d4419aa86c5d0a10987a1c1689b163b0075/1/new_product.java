public static Map<String, String> options(Long projectId) {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Milestone milestone : findMilestones(projectId, MilestoneState.ALL, "title", Direction.ASC)) {
            options.put(milestone.id.toString(), milestone.title);
        }
        return options;
    }