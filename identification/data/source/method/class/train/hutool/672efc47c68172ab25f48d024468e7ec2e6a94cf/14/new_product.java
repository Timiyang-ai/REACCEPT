public static String removeHtmlTag(String content, boolean withTagContent, String... tagNames) {
		String regex = null;
		for (String tagName : tagNames) {
			if (StrUtil.isBlank(tagName)) {
				continue;
			}
			tagName = tagName.trim();
			// (?i)表示其后面的表达式忽略大小写
			if (withTagContent) {
				// 标签及其包含内容
				regex = StrUtil.format("(?i)<{}\\s*?[^>]*?/?>(.*?</{}>)?", tagName, tagName);
			} else {
				// 标签不包含内容
				regex = StrUtil.format("(?i)<{}\\s*?[^>]*?>|</{}>", tagName, tagName);
			}

			content = ReUtil.delAll(regex, content); // 非自闭标签小写
		}
		return content;
	}