    public static Bug createUnpublishedBug(Long longIn, String stringIn) {
        return ErrataFactory.createUnpublishedBug(longIn, stringIn,
                "https://bugzilla.redhat.com/show_bug.cgi?id=" + longIn);
    }