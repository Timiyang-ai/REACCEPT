@VisibleForTesting
    void updateAllPreferenceCategory() {
        updateConnectedPreferenceCategory();

        mPreferenceCategory.removeAll();
        for (int index = 0; index < mCellInfoList.size(); index++) {
            if (!mCellInfoList.get(index).isRegistered()) {
                NetworkOperatorPreference pref = new NetworkOperatorPreference(
                        mCellInfoList.get(index), getContext(), mForbiddenPlmns, mShow4GForLTE);
                pref.setKey(CellInfoUtil.getNetworkTitle(mCellInfoList.get(index)));
                pref.setOrder(index);
                mPreferenceCategory.addPreference(pref);
            }
        }
    }