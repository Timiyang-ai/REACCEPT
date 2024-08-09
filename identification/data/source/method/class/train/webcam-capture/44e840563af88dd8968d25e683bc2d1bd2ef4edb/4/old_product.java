public static void register(IpCamDevice ipcam) {
		for (WebcamDevice d : DEVICES) {
			String name = ipcam.getName();
			if (d.getName().equals(name)) {
				throw new WebcamException(String.format("Name '%s' is already in use", name));
			}
		}
		DEVICES.add(ipcam);
	}