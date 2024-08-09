public static boolean isValidCard(String idCard) {
		idCard = idCard.trim();
		int length = idCard.length();
		switch (length) {
		case 18:// 18位身份证
			return isvalidCard18(idCard);
		case 15:// 15位身份证
			return isvalidCard15(idCard);
		case 10: {// 10位身份证，港澳台地区
			String[] cardval = isValidCard10(idCard);
			if (null != cardval && cardval[2].equals("true")) {
				return true;
			} else {
				return false;
			}
		}
		default:
			return false;
		}
	}