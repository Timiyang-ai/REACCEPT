public static Map<String, String> options() {
        LinkedHashMap<String, String> options = new LinkedHashMap<String, String>();
        for (Milestone milestone : Milestone.find.orderBy("title").findList()) {
            options.put(milestone.id.toString(), milestone.title);
        }
        return options;
    }