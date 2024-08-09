public static boolean unregister(IpCamDevice ipcam) {
		boolean removed = DEVICES.remove(ipcam);

		// run discovery service once if device has been removed to
		// trigger disconnected webcam discovery event and keep webcams
		// list up-to-date

		if (removed) {
			Webcam.getDiscoveryService().scan();
		}

		return removed;
	}