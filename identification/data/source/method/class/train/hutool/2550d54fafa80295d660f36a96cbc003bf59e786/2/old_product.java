public static InetAddress getLocalhost() {
		final LinkedHashSet<InetAddress> localAddressList = localAddressList(new Filter<InetAddress>() {
			@Override
			public boolean accept(InetAddress address) {
				// 非loopback地址，指127.*.*.*的地址
				return false == address.isLoopbackAddress()
						// 非地区本地地址，指10.0.0.0 ~ 10.255.255.255、172.16.0.0 ~ 172.31.255.255、192.168.0.0 ~ 192.168.255.255
						&& false == address.isSiteLocalAddress()
				// 需为IPV4地址
						&& address instanceof Inet4Address;
			}
		});

		if (CollUtil.isNotEmpty(localAddressList)) {
			InetAddress address = CollUtil.get(localAddressList, 0);
			return address;
		}

		try {
			return InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// ignore
		}

		return null;
	}