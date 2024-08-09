public static Bug createNewUnpublishedBug(Long id, String summary, String url) {
        return ErrataFactory.createUnpublishedBug(id, summary, url);
    }