public static IpCamDevice register(IpCamDevice ipcam) {

		for (WebcamDevice d : DEVICES) {
			String name = ipcam.getName();
			if (d.getName().equals(name)) {
				throw new WebcamException(String.format("Webcam with name '%s' is already registered", name));
			}
		}

		DEVICES.add(ipcam);

		// run discovery service once to trigger new webcam discovery event
		// and keep webcams list up-to-date

		Webcam.getDiscoveryService().scan();

		return ipcam;
	}