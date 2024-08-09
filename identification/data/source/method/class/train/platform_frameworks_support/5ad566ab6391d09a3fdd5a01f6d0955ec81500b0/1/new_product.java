public @Nullable List<MediaItem2> getPlaylist() {
        return isConnected() ? getImpl().getPlaylist() : null;
    }