public static int compareVersion(String version, String value) {
		try {
			if (version == null || value == null) {
				return 0;
			}
			
			List<String> versions = new Vector<String>();
			List<String> values = new Vector<String>();
			String separator = "-";
			
			// strip off any qualifier e.g. "-SNAPSHOT"
			int qualifierIndex = version.indexOf(separator);
			if (qualifierIndex != -1) {
				version = version.substring(0, qualifierIndex);
			}
			
			qualifierIndex = value.indexOf(separator);
			if (qualifierIndex != -1) {
				value = value.substring(0, qualifierIndex);
			}
			
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
				Long ver = NumberUtils.toLong(verNum, 0);
				Long val = NumberUtils.toLong(valNum, 0);
				
				int ret = ver.compareTo(val);
				if (ret != 0) {
					return ret;
				}
			}
		}
		catch (NumberFormatException e) {
			log.error("Error while converting a version/value to an integer: " + version + "/" + value, e);
		}
		
		// default return value if an error occurs or elements are equal
		return 0;
	}