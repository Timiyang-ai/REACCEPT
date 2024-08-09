    @Test
    public void formatFileSize_zero() throws Exception {
        assertThat(
                        FileSizeFormatter.formatFileSize(
                                mContext,
                                0 /* size */,
                                com.android.internal.R.string.gigabyteShort,
                                GIGABYTE_IN_BYTES))
                .isEqualTo("0.00 GB");
    }