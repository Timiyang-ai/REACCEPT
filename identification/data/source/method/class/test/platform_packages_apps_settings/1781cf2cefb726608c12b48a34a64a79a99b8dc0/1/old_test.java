    @Test
    public void isAvailable_notIfNull() {
        mController.onResume(null, null, null, null);
        assertFalse(mController.isAvailable());
    }