boolean needRefreshBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        if (bookmarks.size() == currentBookmarks.size()) {
            return false;
        }
        return true;
    }