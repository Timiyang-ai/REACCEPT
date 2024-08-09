public static Bug createNewPublishedBug(Long id, String summary, String url) {
        return ErrataFactory.createPublishedBug(id, summary, url);
    }