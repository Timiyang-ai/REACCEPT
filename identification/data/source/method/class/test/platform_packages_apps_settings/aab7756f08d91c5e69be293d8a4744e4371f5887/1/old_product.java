public EntityHeaderController styleActionBar(Activity activity) {
        if (activity == null) {
            Log.w(TAG, "No activity, cannot style actionbar.");
            return this;
        }
        final ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            Log.w(TAG, "No actionbar, cannot style actionbar.");
            return this;
        }
        actionBar.setBackgroundDrawable(
                new ColorDrawable(Utils.getColorAttr(activity, android.R.attr.colorPrimary)));
        actionBar.setElevation(0);
        if (mRecyclerView != null && mLifecycle != null) {
            ActionBarShadowController.attachToRecyclerView(mActivity, mLifecycle, mRecyclerView);
        }

        return this;
    }