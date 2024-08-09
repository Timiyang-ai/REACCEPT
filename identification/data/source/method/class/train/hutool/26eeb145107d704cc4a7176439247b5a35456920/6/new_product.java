public static String normalize(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = path.replaceAll("[/\\\\]{1,}", "/").trim();

		int prefixIndex = pathToUse.indexOf(StrUtil.COLON);
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if (prefix.contains("/")) {
				prefix = "";
			}else {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}
		}
		if (pathToUse.startsWith(StrUtil.SLASH)) {
			prefix = prefix + StrUtil.SLASH;
			pathToUse = pathToUse.substring(1);
		}

		List<String> pathList = StrUtil.split(pathToUse, StrUtil.C_SLASH);
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathList.size() - 1; i >= 0; i--) {
			String element = pathList.get(i);
			if (StrUtil.DOT.equals(element)) {
				//当前目录，丢弃
			}else if (StrUtil.DOUBLE_DOT.equals(element)) {
				tops++;
			}else {
				if (tops > 0) {
					// Merging path element with element corresponding to top path.
					tops--;
				}else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, StrUtil.DOUBLE_DOT);
		}

		return prefix + CollectionUtil.join(pathElements, StrUtil.SLASH);
	}