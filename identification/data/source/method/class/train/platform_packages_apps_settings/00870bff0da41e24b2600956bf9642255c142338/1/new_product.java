@Nullable
    public static String getCallingAppPackageName(IBinder activityToken) {
        String pkg = null;
        try {
            pkg = ActivityManager.getService().getLaunchedFromPackage(activityToken);
        } catch (RemoteException e) {
            Log.v(TAG, "Could not talk to activity manager.", e);
        }
        return pkg;
    }