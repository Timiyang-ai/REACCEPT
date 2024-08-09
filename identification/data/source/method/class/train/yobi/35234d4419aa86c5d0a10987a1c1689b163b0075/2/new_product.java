public static Map<String, String> options(Long projectId) {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Milestone milestone : Milestone.find.where()
                .eq("projectId", projectId).orderBy("title").findList()) {
            options.put(milestone.id.toString(), milestone.title);
        }
        return options;
    }