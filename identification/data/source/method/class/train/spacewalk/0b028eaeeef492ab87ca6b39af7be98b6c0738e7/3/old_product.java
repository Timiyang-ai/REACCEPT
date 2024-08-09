public static Bug createPublishedBug(Long id, String summary) {
        Bug bug = new PublishedBug();
        bug.setId(id);
        bug.setSummary(summary);
        return bug;
    }