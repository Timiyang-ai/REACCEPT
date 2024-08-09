public static String format(double amount, boolean isUseTraditional, boolean isMoneyMode) {
		final String[] numArray = isUseTraditional ? traditionalDigits : simpleDigits;

		if (amount > 99999999999999.99 || amount < -99999999999999.99) {
			throw new IllegalArgumentException("Number support only: (-99999999999999.99 ～ 99999999999999.99)！");
		}

		boolean negative = false;
		if (amount < 0) {
			negative = true;
			amount = - amount;
		}

		long temp = Math.round(amount * 100);
		int numFen = (int) (temp % 10);
		temp = temp / 10;
		int numJiao = (int) (temp % 10);
		temp = temp / 10;

		//将数字以万为单位分为多份
		int[] parts = new int[20];
		int numParts = 0;
		for (int i = 0; temp != 0; i++) {
			int part = (int) (temp % 10000);
			parts[i] = part;
			numParts++;
			temp = temp / 10000;
		}

		boolean beforeWanIsZero = true; // 标志“万”下面一级是不是 0

		String chineseStr = "";
		for (int i = 0; i < numParts; i++) {
			String partChinese = toChinese(parts[i], isUseTraditional);
			if (i % 2 == 0) {
				beforeWanIsZero = StrUtil.isEmpty(partChinese);
			}

			if (i != 0) {
				if (i % 2 == 0) {
					chineseStr = "亿" + chineseStr;
				} else {
					if ("".equals(partChinese) && false == beforeWanIsZero) {
						// 如果“万”对应的 part 为 0，而“万”下面一级不为 0，则不加“万”，而加“零”
						chineseStr = "零" + chineseStr;
					} else {
						if (parts[i - 1] < 1000 && parts[i - 1] > 0) {
							// 如果"万"的部分不为 0, 而"万"前面的部分小于 1000 大于 0， 则万后面应该跟“零”
							chineseStr = "零" + chineseStr;
						}
						chineseStr = "万" + chineseStr;
					}
				}
			}
			chineseStr = partChinese + chineseStr;
		}

		if ("".equals(chineseStr)) // 整数部分为 0, 则表达为"零"
			chineseStr = numArray[0];
		else if (negative) { // 整数部分不为 0
			chineseStr = "负" + chineseStr;
		}

		// 小数部分
		if (numFen != 0 || numJiao != 0) {
			if (numFen == 0) {
				chineseStr += (isMoneyMode ? "元" : "点") + numArray[numJiao] + (isMoneyMode ? "角" : "");
			} else { // “分”数不为 0
				if (numJiao == 0) {
					chineseStr += (isMoneyMode ? "元零" : "点零") + numArray[numFen] + (isMoneyMode ? "角" : "");
				} else {
					chineseStr += (isMoneyMode ? "元" : "点") + numArray[numJiao] + (isMoneyMode ? "角" : "") + numArray[numFen] + (isMoneyMode ? "分" : "");
				}
			}
		}else if(isMoneyMode) {
			//无小数部分的金额结尾
			chineseStr += "整";
		}

		return chineseStr;

	}