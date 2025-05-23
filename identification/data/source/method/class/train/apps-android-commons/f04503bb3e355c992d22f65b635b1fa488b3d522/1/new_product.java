List<Media> loadBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        currentBookmarks = bookmarks;
        ArrayList<Media> medias = new ArrayList<>();
        for (Bookmark bookmark : bookmarks) {
            List<Media> tmpMedias = okHttpJsonApiClient
                    .getMediaList("search", bookmark.getMediaName())
                    .blockingGet();
            for (Media m : tmpMedias) {
                if (m.getCreator().trim().equals(bookmark.getMediaCreator().trim())) {
                    medias.add(m);
                    break;
                }
            }
        }
        return medias;
    }