    @Test
    public void getCacheSize() {
        System.out.println(FileUtils.getLength(DISK1_FILE));
        assertEquals(FileUtils.getLength(DISK1_FILE), CACHE_DISK_UTILS1.getCacheSize());

        System.out.println(FileUtils.getLength(DISK2_FILE));
        assertEquals(FileUtils.getLength(DISK2_FILE), CACHE_DISK_UTILS2.getCacheSize());
    }