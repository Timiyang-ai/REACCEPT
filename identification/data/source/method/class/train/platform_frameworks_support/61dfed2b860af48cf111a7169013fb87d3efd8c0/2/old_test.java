    @Test
    public void isTransitionGroup() {
        assertFalse(ViewGroupCompat.isTransitionGroup(mViewGroup));
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                ViewCompat.setBackground(mViewGroup, new ColorDrawable(Color.GRAY));
            }
        });
        assertTrue(ViewGroupCompat.isTransitionGroup(mViewGroup));
    }