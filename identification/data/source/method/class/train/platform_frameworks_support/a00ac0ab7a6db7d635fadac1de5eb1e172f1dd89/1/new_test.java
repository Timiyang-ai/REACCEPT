@Test
    public void testSkipToEnd() {
        final float finalPosition = 10f;
        final SpringAnimation anim = new SpringAnimation(mView1, DynamicAnimation.SCROLL_X,
                finalPosition);
        final DynamicAnimation.OnAnimationEndListener mockListener =
                mock(DynamicAnimation.OnAnimationEndListener.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                anim.addEndListener(mockListener).setStartValue(200).start();
            }
        });
        assertTrue(anim.isRunning());
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                int scrollX = mView1.getScrollX();
                anim.skipToEnd();
                // Expect no change in the animation values until next frame.
                assertEquals(scrollX, mView1.getScrollX());
                assertTrue(anim.isRunning());
            }
        });
        verify(mockListener, timeout(100).times(1)).onAnimationEnd(anim, false, finalPosition, 0);

        // Also make sure the skipToEnd() call doesn't affect next animation run.
        final DynamicAnimation.OnAnimationEndListener mockListener2 =
                mock(DynamicAnimation.OnAnimationEndListener.class);
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                anim.addEndListener(mockListener2);
                anim.animateToFinalPosition(finalPosition + 1000f);
            }
        });
        // Verify that the animation doesn't finish right away
        verify(mockListener2, timeout(300).times(0)).onAnimationEnd(any(DynamicAnimation.class),
                any(boolean.class), any(float.class), any(float.class));

        // But the animation should eventually finish.
        verify(mockListener, timeout(1000).times(1)).onAnimationEnd(anim, false,
                finalPosition + 1000f, 0);

    }