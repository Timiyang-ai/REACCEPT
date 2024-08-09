public static Map<Integer,String> extractURLParameterValues(String path,Pattern pattern) {
		Matcher matcher = pattern.matcher(path);
		Set<String> names = new LinkedHashSet<String>();
		int count = matcher.groupCount();
		while(matcher.find()) {
			for(int i = 0; i < count; i++) {
				int index = i+1;
				String value = matcher.group(index);
				if(!value.equals(path)) {
					names.add(value);
				}
			}
		}
		String[] namesArray = names.toArray(new String[names.size()]);
		Map<Integer,String> values = new HashMap<Integer,String>();
		for(int i = 0; i < namesArray.length; i++) {
			values.put((i+1),namesArray[i]);
		}
		return values;
	}