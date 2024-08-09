    public static Bug createNewPublishedBug(Long id, String summary) {
        return ErrataManager.createNewPublishedBug(id, summary,
                "https://bugzilla.redhat.com/show_bug.cgi?id=" + id);
    }