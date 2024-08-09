@Test
    public void testGetPlaylist() throws Exception {
        prepareLooper();
        final List<MediaItem2> testList = MediaTestUtils.createPlaylist(2);
        final List<QueueItem> testQueue = MediaUtils2.convertToQueueItemList(testList);
        final AtomicReference<List<MediaItem2>> listFromCallback = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);

        final ControllerCallback callback = new ControllerCallback() {
            @Override
            public void onPlaylistChanged(MediaController2 controller,
                    List<MediaItem2> playlist, MediaMetadata2 metadata) {
                assertNotNull(playlist);
                assertEquals(testList.size(), playlist.size());
                for (int i = 0; i < playlist.size(); i++) {
                    assertEquals(testList.get(i).getMediaId(), playlist.get(i).getMediaId());
                }
                listFromCallback.set(playlist);
                latch.countDown();
            }
        };
        mController = createController(mToken2, true, callback);

        mSession.setQueue(testQueue);
        assertTrue(latch.await(TIMEOUT_MS, TimeUnit.MILLISECONDS));
        assertEquals(listFromCallback.get(), mController.getPlaylist());
    }