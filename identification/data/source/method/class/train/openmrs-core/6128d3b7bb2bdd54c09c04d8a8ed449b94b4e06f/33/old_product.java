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
				String verNum = versions.get(x).trim();
				String valNum = values.get(x).trim();
				Integer ver = new Integer(verNum == "" ? "0" : verNum);
				Integer val = new Integer(valNum == "" ? "0" : valNum);
				
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