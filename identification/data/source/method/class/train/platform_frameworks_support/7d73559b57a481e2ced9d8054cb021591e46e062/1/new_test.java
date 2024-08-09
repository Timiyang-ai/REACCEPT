@UiThreadTest
    @Test
    public void testSetInterpolator() {
        mAnim1.setRepeatCount(ValueAnimator.INFINITE);
        Animator[] animatorArray = {mAnim1, mAnim2};

        Interpolator interpolator = new AccelerateDecelerateInterpolator();
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animatorArray);
        mAnimatorSet.setInterpolator(interpolator);

        assertFalse(mAnimatorSet.isRunning());
        mAnimatorSet.start();

        ArrayList<Animator> animatorList = mAnimatorSet.getChildAnimations();
        assertEquals(interpolator, animatorList.get(0).getInterpolator());
        assertEquals(interpolator, animatorList.get(1).getInterpolator());
        mAnimatorSet.cancel();
    }