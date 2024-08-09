public static String normalize(String path) {
		if (path == null) {
			return null;
		}
		
		// 兼容Spring风格的ClassPath路径，去除前缀，不区分大小写
		String pathToUse = StrUtil.removePrefixIgnoreCase(path, "classpath:");
		//去除file:前缀
		pathToUse = StrUtil.removePrefixIgnoreCase(pathToUse, "file:");
		//统一使用斜杠
		pathToUse = pathToUse.replaceAll("[/\\\\]{1,}", "/").trim();

		int prefixIndex = pathToUse.indexOf(StrUtil.COLON);
		String prefix = "";
		if (prefixIndex > -1) {
			//可能Windows风格路径
			prefix = pathToUse.substring(0, prefixIndex + 1);
			if(StrUtil.startWith(prefix, StrUtil.C_SLASH)) {
				//去除类似于/C:这类路径开头的斜杠
				prefix = prefix.substring(1);
			}
			if (false == prefix.contains("/")) {
				pathToUse = pathToUse.substring(prefixIndex + 1);
			}else {
				//如果前缀中包含/,说明非Windows风格path
				prefix = StrUtil.EMPTY;
			}
		}
		if (pathToUse.startsWith(StrUtil.SLASH)) {
			prefix += StrUtil.SLASH;
			pathToUse = pathToUse.substring(1);
		}

		List<String> pathList = StrUtil.split(pathToUse, StrUtil.C_SLASH);
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		String element;
		for (int i = pathList.size() - 1; i >= 0; i--) {
			element = pathList.get(i);
			if (StrUtil.DOT.equals(element)) {
				// 当前目录，丢弃
			} else if (StrUtil.DOUBLE_DOT.equals(element)) {
				tops++;
			} else {
				if (tops > 0) {
					// 有上级目录标记时按照个数依次跳过
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		return prefix + CollUtil.join(pathElements, StrUtil.SLASH);
	}