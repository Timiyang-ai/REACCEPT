    @Test
    public void setTransitionGroup() {
        assertFalse(ViewGroupCompat.isTransitionGroup(mViewGroup));
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                ViewGroupCompat.setTransitionGroup(mViewGroup, true);
            }
        });
        assertTrue(ViewGroupCompat.isTransitionGroup(mViewGroup));
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                ViewGroupCompat.setTransitionGroup(mViewGroup, false);
            }
        });
        assertFalse(ViewGroupCompat.isTransitionGroup(mViewGroup));
    }