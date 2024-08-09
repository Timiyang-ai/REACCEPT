public static InetAddress getLocalhost() {
		InetAddress candidateAddress = null;
		NetworkInterface iface;
		InetAddress inetAddr;
		try {
			for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
				iface = ifaces.nextElement();
				for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
					inetAddr = inetAddrs.nextElement();
					if (false == inetAddr.isLoopbackAddress()) {
						if (inetAddr.isSiteLocalAddress()) {
							return inetAddr;
						}else if(null == candidateAddress){
							//非site-local地址做为候选地址返回
							candidateAddress = inetAddr;
						}
					}
				}
			}
		} catch (SocketException e) {
			//ignore socket exception, and return null;
		}
		
		if(null == candidateAddress){
			try {
				candidateAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				//ignore
			}
		}
		
		return candidateAddress;
	}