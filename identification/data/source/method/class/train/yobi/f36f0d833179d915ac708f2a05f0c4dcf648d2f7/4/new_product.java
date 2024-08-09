public static Map<String, String> options() {
        LinkedHashMap<String, String> options = new LinkedHashMap<>();
        for (User user : User.find.orderBy("name").findList()) {
            options.put(user.id.toString(), user.name);
        }
        return options;
    }