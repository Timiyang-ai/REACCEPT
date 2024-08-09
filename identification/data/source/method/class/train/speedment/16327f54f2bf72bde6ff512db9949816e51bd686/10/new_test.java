    @Test
    void onClose() {
        final AtomicInteger cnt = new AtomicInteger();
        instance.onClose(cnt::incrementAndGet);
        instance.close();
        assertEquals(1, cnt.get());
    }