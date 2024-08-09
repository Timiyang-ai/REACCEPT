@Test
    public void testSkipToEnd() {
        final SpringAnimation anim = new SpringAnimation(mView1, DynamicAnimation.SCROLL_X, 0.0f);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                anim.setStartValue(200).start();
            }
        });
        assertTrue(anim.isRunning());
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                anim.skipToEnd();
            }
        });
        assertFalse(anim.isRunning());
        assertEquals(0, mView1.getScrollX());
    }