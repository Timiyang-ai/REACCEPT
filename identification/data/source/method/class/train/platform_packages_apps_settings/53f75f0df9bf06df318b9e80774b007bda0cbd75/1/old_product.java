@Override
    public Slice getSlice() {
        final IconCompat icon = IconCompat.createWithResource(mContext,
                R.drawable.ic_homepage_storage);
        final String title = mContext.getString(R.string.storage_label);
        final SliceAction primaryAction = new SliceAction(getPrimaryAction(), icon, title);
        final PrivateStorageInfo info = getPrivateStorageInfo();
        return new ListBuilder(mContext, STORAGE_CARD_URI, ListBuilder.INFINITY)
                .setAccentColor(Utils.getColorAccentDefaultColor(mContext))
                .setHeader(new ListBuilder.HeaderBuilder().setTitle(title))
                .addRow(new ListBuilder.RowBuilder()
                        .setTitle(getStorageUsedText(info))
                        .setSubtitle(getStorageSummaryText(info))
                        .setPrimaryAction(primaryAction))
                .build();
    }