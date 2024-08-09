public static String removeHtmlTag(String content, boolean withTagContent, String... tagNames) {
		String regex1 = null;
		String regex2 = null;
		for (String tagName : tagNames) {
			if (StrUtil.isBlank(tagName)) {
				continue;
			}
			tagName = tagName.trim();
			// (?i)表示其后面的表达式忽略大小写
			regex1 = StrUtil.format("(?i)<{}\\s?[^>]*?/>", tagName);
			if (withTagContent) {
				// 标签及其包含内容
				regex2 = StrUtil.format("(?i)(?s)<{}\\s*?[^>]*?>.*?</{}>", tagName, tagName);
			} else {
				// 标签不包含内容
				regex2 = StrUtil.format("(?i)<{}\\s*?[^>]*?>|</{}>", tagName, tagName);
			}

			content = content.replaceAll(regex1, StrUtil.EMPTY) // 自闭标签小写
					.replaceAll(regex2, StrUtil.EMPTY); // 非自闭标签小写
		}
		return content;
	}