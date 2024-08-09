    @Test
    public void getSerializable() {
        assertEquals(SERIALIZABLE_TEST, CACHE_DISK_UTILS1.getSerializable("serializable1"));
        assertEquals(SERIALIZABLE_TEST, CACHE_DISK_UTILS1.getSerializable("serializable1", null));
        assertNull(CACHE_DISK_UTILS1.getSerializable("parcelable2", null));

        assertEquals(SERIALIZABLE_TEST, CACHE_DISK_UTILS2.getSerializable("serializable2"));
        assertEquals(SERIALIZABLE_TEST, CACHE_DISK_UTILS2.getSerializable("serializable2", null));
        assertNull(CACHE_DISK_UTILS2.getSerializable("parcelable1", null));
    }