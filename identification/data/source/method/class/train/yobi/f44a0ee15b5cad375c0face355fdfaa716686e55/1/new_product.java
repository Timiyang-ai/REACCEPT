public static Map<String, String> options(Long projectId) {
        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        for (User user : User.findUsersByProject(projectId)) {
            options.put(user.id.toString(), user.loginId);
        }
        return options;
    }