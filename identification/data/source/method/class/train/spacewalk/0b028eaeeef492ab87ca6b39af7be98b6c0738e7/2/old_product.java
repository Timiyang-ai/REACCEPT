public static Bug createUnpublishedBug(Long id, String summary) {
        Bug bug = new UnpublishedBug();
        bug.setId(id);
        bug.setSummary(summary);
        return bug;
    }