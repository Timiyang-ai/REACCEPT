public static boolean unregister(IpCamDevice ipcam) {
		try {
			return DEVICES.remove(ipcam);
		} finally {
			rescan();
		}
	}