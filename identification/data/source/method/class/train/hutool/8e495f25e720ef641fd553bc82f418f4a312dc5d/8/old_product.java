public static List<String> split(String str, char separator, int limit) {
		if (str == null) {
			return null;
		}
		List<String> list = new ArrayList<String>(limit == 0 ? 16 : limit);
		if (limit == 1) {
			list.add(str);
			return list;
		}

		boolean isNotEnd = true; // 未结束切分的标志
		int strLen = str.length();
		StringBuilder sb = new StringBuilder(strLen);
		for (int i = 0; i < strLen; i++) {
			char c = str.charAt(i);
			if (isNotEnd && c == separator) {
				list.add(sb.toString());
				// 清空StringBuilder
				sb.delete(0, sb.length());

				// 当达到切分上限-1的量时，将所剩字符全部作为最后一个串
				if (limit != 0 && list.size() == limit - 1) {
					isNotEnd = false;
				}
			} else {
				sb.append(c);
			}
		}
		list.add(sb.toString());// 加入尾串
		return list;
	}