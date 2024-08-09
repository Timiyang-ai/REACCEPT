public static Bug createPublishedBug(Long id, String summary, String url) {
        Bug bug = new PublishedBug();
        bug.setId(id);
        bug.setSummary(summary);
        bug.setUrl(url);
        return bug;
    }