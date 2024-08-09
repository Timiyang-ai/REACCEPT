protected final void displayTilesAsPreference(String TAG, PreferenceScreen screen,
            DashboardCategory category) {
        final Context context = getContext();
        List<Tile> tiles = category.tiles;
        if (tiles == null) {
            Log.d(TAG, "tile list is empty, skipping category " + category.title);
            return;
        }
        for (Tile tile : tiles) {
            final String key = mDashboardFeatureProvider.getDashboardKeyForTile(tile);
            if (TextUtils.isEmpty(key)) {
                Log.d(TAG, "tile does not contain a key, skipping " + tile);
                continue;
            }
            final Preference pref = new DashboardTilePreference(context);
            pref.setTitle(tile.title);
            pref.setKey(key);
            pref.setSummary(tile.summary);
            if (tile.icon != null) {
                pref.setIcon(tile.icon.loadDrawable(context));
            }
            if (tile.intent != null) {
                pref.setIntent(tile.intent);
            }
            // Use negated priority for order, because tile priority is based on intent-filter
            // (larger value has higher priority). However pref order defines smaller value has
            // higher priority.
            pref.setOrder(-tile.priority);
            screen.addPreference(pref);
        }
    }