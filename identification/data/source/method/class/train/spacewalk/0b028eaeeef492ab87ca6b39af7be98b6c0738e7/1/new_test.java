    public static Bug createNewUnpublishedBug(Long id, String summary) {
        return ErrataManager.createNewUnpublishedBug(id, summary,
                "https://bugzilla.redhat.com/show_bug.cgi?id=" + id);
    }