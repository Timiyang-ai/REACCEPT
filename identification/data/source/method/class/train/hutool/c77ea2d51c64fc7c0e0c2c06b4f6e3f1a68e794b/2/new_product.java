public static Map<String, List<String>> decodeParams(String paramsStr, String charset) {
		if (StrUtil.isBlank(paramsStr)) {
			return Collections.emptyMap();
		}

		// 去掉Path部分
		int pathEndPos = paramsStr.indexOf('?');
		if (pathEndPos > -1) {
			paramsStr = StrUtil.subSuf(paramsStr, pathEndPos + 1);
			if (StrUtil.isBlank(paramsStr)) {
				return Collections.emptyMap();
			}
		}

		final int len = paramsStr.length();
		final Map<String, List<String>> params = new LinkedHashMap<>();
		String name = null;
		int pos = 0; // 未处理字符开始位置
		int i; // 未处理字符结束位置
		char c; // 当前字符
		for (i = 0; i < len; i++) {
			c = paramsStr.charAt(i);
			if (c == '=') { // 键值对的分界点
				if (null == name) {
					// name可以是""
					name = paramsStr.substring(pos, i);
				}
				pos = i + 1;
			} else if (c == '&') { // 参数对的分界点
				if (null == name && pos != i) {
					// 对于像&a&这类无参数值的字符串，我们将name为a的值设为""
					addParam(params, paramsStr.substring(pos, i), StrUtil.EMPTY, charset);
				} else if (name != null) {
					addParam(params, name, paramsStr.substring(pos, i), charset);
					name = null;
				}
				pos = i + 1;
			}
		}

		// 处理结尾
		if (pos != i) {
			if (name == null) {
				addParam(params, paramsStr.substring(pos, i), StrUtil.EMPTY, charset);
			} else {
				addParam(params, name, paramsStr.substring(pos, i), charset);
			}
		} else if (name != null) {
			addParam(params, name, StrUtil.EMPTY, charset);
		}

		return params;
	}