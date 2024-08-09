@Test
    @SmallTest
    public void testSetShuffleModeEnabled() throws Exception {
        final boolean shuffleModeEnabled = true;
        MediaControllerCompat controller = mSession.getController();
        controller.registerCallback(mCallback, mHandler);
        synchronized (mWaitLock) {
            mCallback.resetLocked();
            mSession.setShuffleModeEnabled(shuffleModeEnabled);
            mWaitLock.wait(TIME_OUT_MS);
            assertTrue(mCallback.mOnShuffleModeChangedDeprecatedCalled);
            assertEquals(shuffleModeEnabled, mCallback.mShuffleModeEnabled);
            assertEquals(shuffleModeEnabled, controller.isShuffleModeEnabled());
        }
    }