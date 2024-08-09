    @Test
    public void getSlice_shouldBeCorrectSliceContent() {
        final PrivateStorageInfo info = new PrivateStorageInfo(100L, 600L);
        doReturn(info).when(mStorageSlice).getPrivateStorageInfo();
        doReturn(USED_BYTES_TEXT).when(mStorageSlice).getStorageUsedText(any());
        doReturn(SUMMARY_TEXT).when(mStorageSlice).getStorageSummaryText(any());

        final Slice slice = mStorageSlice.getSlice();

        final SliceMetadata metadata = SliceMetadata.from(mContext, slice);
        assertThat(metadata.getTitle()).isEqualTo(mContext.getString(R.string.storage_label));

        final SliceAction primaryAction = metadata.getPrimaryAction();
        final IconCompat expectedIcon = IconCompat.createWithResource(mContext,
                R.drawable.ic_homepage_storage);
        assertThat(primaryAction.getIcon().toString()).isEqualTo(expectedIcon.toString());
    }