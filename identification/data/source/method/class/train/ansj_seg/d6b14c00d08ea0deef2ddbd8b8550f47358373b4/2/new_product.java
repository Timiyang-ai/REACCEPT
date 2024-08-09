public static List<Element> wordAlert(String word) {

		char[] chars = WordAlert.alertStr(word);

		List<Element> list = new ArrayList<Element>();

		StringBuilder tempSb = new StringBuilder();

		int status = 0; // 1 num 2 english

		Element element = null;

		for (int i = 0; i < chars.length; i++) {

			if (chars[i] >= '0' && chars[i] <= '9') {
				if (status == 2) {
					element = new Element(Config.getNum(tempSb.toString()));
					element.len = tempSb.length();
					list.add(element);
					tempSb = new StringBuilder();
				}
				tempSb.append(chars[i]);
				status = 1;
			} else if (chars[i] >= 'A' && chars[i] <= 'z') {
				if (status == 1) {
					element = new Element(Config.getEn(tempSb.toString()));
					element.len = tempSb.length();
					list.add(element);
					tempSb = new StringBuilder();
				}
				tempSb.append(chars[i]);
				status = 2;
			} else {
				if (status == 1) {
					element = new Element(Config.getNum(tempSb.toString()));
					element.len = tempSb.length();
					list.add(element);
				} else if (status == 2) {
					element = new Element(Config.getEn(tempSb.toString()));
					element.len = tempSb.length();
					list.add(element);
				}
				tempSb = new StringBuilder();
				list.add(new Element(chars[i]));
				status = 0;
			}

		}

		if (tempSb.length() > 0) {
			if (status == 1) {
				element = new Element(Config.getNum(tempSb.toString()));
				element.len = tempSb.length();
				list.add(element);
			} else if (status == 2) {
				element = new Element(Config.getEn(tempSb.toString()));
				element.len = tempSb.length();
				list.add(element);
			} else {
				LOG.error("err! status :"+status);
			}
		}

		return list;
	}