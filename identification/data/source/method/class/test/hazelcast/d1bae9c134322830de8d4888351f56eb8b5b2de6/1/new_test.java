    @Test
    public void getAsReadOnly() {
        RingbufferStoreConfig ringbufferStoreConfig = new RingbufferStoreConfig();

        MergePolicyConfig mergePolicyConfig = new MergePolicyConfig()
                .setPolicy(PassThroughMergePolicy.class.getName())
                .setBatchSize(2342);

        RingbufferConfig original = new RingbufferConfig(NAME)
                .setBackupCount(2)
                .setAsyncBackupCount(1)
                .setCapacity(10)
                .setTimeToLiveSeconds(400)
                .setRingbufferStoreConfig(ringbufferStoreConfig)
                .setMergePolicyConfig(mergePolicyConfig);

        RingbufferConfig readonly = new RingbufferConfigReadOnly(original);
        assertNotNull(readonly);

        assertEquals(original.getName(), readonly.getName());
        assertEquals(original.getBackupCount(), readonly.getBackupCount());
        assertEquals(original.getAsyncBackupCount(), readonly.getAsyncBackupCount());
        assertEquals(original.getCapacity(), readonly.getCapacity());
        assertEquals(original.getTimeToLiveSeconds(), readonly.getTimeToLiveSeconds());
        assertEquals(original.getInMemoryFormat(), readonly.getInMemoryFormat());
        assertEquals(original.getRingbufferStoreConfig(), readonly.getRingbufferStoreConfig());
        assertFalse("The read-only RingbufferStoreConfig should not be identity-equal to the original RingbufferStoreConfig",
                original.getRingbufferStoreConfig() == readonly.getRingbufferStoreConfig());
        assertEquals(original.getMergePolicyConfig(), readonly.getMergePolicyConfig());

        try {
            readonly.setCapacity(10);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setAsyncBackupCount(1);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setBackupCount(1);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setTimeToLiveSeconds(1);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setInMemoryFormat(InMemoryFormat.OBJECT);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setRingbufferStoreConfig(null);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.getRingbufferStoreConfig().setEnabled(true);
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        try {
            readonly.setMergePolicyConfig(new MergePolicyConfig());
            fail();
        } catch (UnsupportedOperationException expected) {
        }

        original.setRingbufferStoreConfig(null);
        readonly = new RingbufferConfigReadOnly(original);

        assertNotNull(readonly.getRingbufferStoreConfig());
        assertFalse(readonly.getRingbufferStoreConfig().isEnabled());
    }