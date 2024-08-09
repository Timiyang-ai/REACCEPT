    @Test
    public void needRefreshBookmarkedPictures() {
        boolean needRefreshBookmarkedPictures = bookmarkPicturesController.needRefreshBookmarkedPictures();
        assertTrue(needRefreshBookmarkedPictures);
    }