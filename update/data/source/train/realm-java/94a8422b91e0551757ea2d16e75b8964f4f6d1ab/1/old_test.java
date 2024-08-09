@Test
    public void addListener_shouldBeCalledByWaitForChangeThenRefresh() {
        final CountDownLatch latch = new CountDownLatch(1);
        Collection collection = new Collection(sharedRealm, table.where());
        collection.size();
        collection.addListener(collection, new RealmChangeListener<Collection>() {
            @Override
            public void onChange(Collection element) {
                assertEquals(latch.getCount(), 1);
                latch.countDown();
            }
        });

        addRowAsync();

        sharedRealm.waitForChange();
        sharedRealm.refresh();
        TestHelper.awaitOrFail(latch);
    }