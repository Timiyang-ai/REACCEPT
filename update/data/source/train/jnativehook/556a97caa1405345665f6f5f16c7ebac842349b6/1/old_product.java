public static void unregisterNativeHook() {
		GlobalScreen.hookThread.signal();
	}