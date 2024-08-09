Single<List<Media>> loadBookmarkedPictures() {
        List<Bookmark> bookmarks = bookmarkDao.getAllBookmarks();
        currentBookmarks = bookmarks;
        return Observable.fromIterable(bookmarks)
                .flatMap((Function<Bookmark, ObservableSource<Media>>) this::getMediaFromBookmark)
                .filter(media -> media != null && !StringUtils.isBlank(media.getFilename()))
                .toList();
    }