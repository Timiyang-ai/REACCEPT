public static Bug createUnpublishedBug(Long id, String summary, String url) {
        Bug bug = new UnpublishedBug();
        bug.setId(id);
        bug.setSummary(summary);
        bug.setUrl(url);
        return bug;
    }