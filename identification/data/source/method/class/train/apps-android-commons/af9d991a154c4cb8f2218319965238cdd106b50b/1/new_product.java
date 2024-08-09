boolean needRefreshBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        return bookmarks.size() != currentBookmarks.size();
    }