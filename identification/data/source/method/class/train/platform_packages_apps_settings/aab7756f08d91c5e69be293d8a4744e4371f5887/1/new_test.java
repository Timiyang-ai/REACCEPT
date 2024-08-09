    @Test
    public void styleActionBar_setElevationAndBackground() {
        final ActionBar actionBar = mActivity.getActionBar();

        mController = EntityHeaderController.newInstance(mActivity, mFragment, null);
        mController.styleActionBar(mActivity);

        verify(actionBar).setElevation(0);
        // Enforce a color drawable as background here, as image based drawables might not be
        // wide enough to cover entire action bar.
        verify(actionBar).setBackgroundDrawable(any(ColorDrawable.class));
    }