public static int compareVersion(String version, String value) {
		try {
			
			List<String> versions = new Vector<String>();
			List<String> values = new Vector<String>();
			
			Collections.addAll(versions, version.split("\\."));
			Collections.addAll(values, value.split("\\."));
			
			// match the sizes of the lists
			while (versions.size() < values.size()) {
				versions.add("0");
			}
			while (values.size() < versions.size()) {
				values.add("0");
			}
			
			for (int x = 0; x < versions.size(); x++) {
				Integer ver = new Integer(versions.get(x).trim());
				Integer val = new Integer(values.get(x).trim());
				
				int ret = ver.compareTo(val);
				if (ret != 0)
					return ret;
			}
		} catch (NumberFormatException e) {
			log.error("Error while converting a version/value to an integer: " + version + "/" + value, e);
		}
		
		// default return value if an error occurs or elements are equal
		return 0;
	}