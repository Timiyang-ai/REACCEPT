static void parse (Map<Application.Name, List<List<String>>> body) {
        Date now = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXX");
        String timestamp = df.format(now);

        for (List<List<String>> submissions : body.values()) {
            for (List<String> submission : submissions) {
                if (submission.get(0) == null) {
                    submission.set(0, timestamp);
                }
            }
        }
    }