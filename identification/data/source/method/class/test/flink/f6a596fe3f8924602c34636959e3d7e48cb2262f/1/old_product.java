public static <T> MasterTriggerRestoreHook<T> wrapHook(MasterTriggerRestoreHook<T> hook, ClassLoader userClassLoader) {
		return new WrappedMasterHook<T>(hook, userClassLoader);
	}