    @Test
    public void loadBookmarkedPictures() {
        List<Media> bookmarkedPictures = bookmarkPicturesController.loadBookmarkedPictures().blockingGet();
        assertEquals(2, bookmarkedPictures.size());
    }