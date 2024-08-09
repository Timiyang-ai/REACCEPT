public static Map<String,Object> omit(Map<String,Object> params, String... toOmit) {
		if (params == null) return null;
		if (toOmit == null || toOmit.length == 0) return params;

		Map<String,Object> ret = new HashMap<String,Object>();

		for (String key : params.keySet()) {
			if (!ArrayUtils.contains(toOmit, key))
				ret.put(key, params.get(key));
		}
		return ret;
	}