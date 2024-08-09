public static boolean isFileExists(final String filePath) {
        if (Build.VERSION.SDK_INT < 29) {
            return isFileExists(getFileByPath(filePath));
        } else {
            try {
                Uri uri = Uri.parse(filePath);
                ContentResolver cr = Utils.getApp().getContentResolver();
                AssetFileDescriptor afd = cr.openAssetFileDescriptor(uri, "r");
                if (afd == null) return false;
                try {
                    afd.close();
                } catch (IOException ignore) {
                }
            } catch (FileNotFoundException e) {
                return false;
            }
            return true;
        }
    }