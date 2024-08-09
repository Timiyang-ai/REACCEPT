List<Media> loadBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        currentBookmarks = bookmarks;
        ArrayList<Media> medias = new ArrayList<Media>();
        for (Bookmark bookmark : bookmarks) {
            List<Media> tmpMedias = mediaWikiApi.searchImages(bookmark.getMediaName(), 0);
            for (Media m : tmpMedias) {
                if (m.getCreator().trim().equals(bookmark.getMediaCreator().trim())) {
                    medias.add(m);
                    break;
                }
            }
        }
        return medias;
    }