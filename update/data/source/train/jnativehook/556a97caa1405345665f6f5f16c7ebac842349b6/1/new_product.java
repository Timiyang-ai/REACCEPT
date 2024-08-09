public static void unregisterNativeHook() throws NativeHookException {
		if (isNativeHookRegistered()) {
			synchronized (hookThread) {
				hookThread.disable();

				try {
					hookThread.join();
				}
				catch (InterruptedException e) {
					throw new NativeHookException(e.getCause());
				}
			}
		}
	}