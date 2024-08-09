public static String format(double amount, boolean isUserTraditional) {
		final String[] numArray = isUserTraditional ? traditionalDigits : simpleDigits;

		if (amount > 99999999999999.99 || amount < -99999999999999.99) {
			throw new IllegalArgumentException("参数值超出允许范围 (-99999999999999.99 ～ 99999999999999.99)！");
		}

		boolean negative = false;
		if (amount < 0) {
			negative = true;
			amount = amount * (-1);
		}

		long temp = Math.round(amount * 100);
		int numFen = (int) (temp % 10);
		temp = temp / 10;
		int numJiao = (int) (temp % 10);
		temp = temp / 10;

		int[] parts = new int[20];
		int numParts = 0;
		for (int i = 0;; i++) {
			if (temp == 0)
				break;
			int part = (int) (temp % 10000);
			parts[i] = part;
			numParts++;
			temp = temp / 10000;
		}

		boolean beforeWanIsZero = true; // 标志“万”下面一级是不是 0

		String chineseStr = "";
		for (int i = 0; i < numParts; i++) {
			String partChinese = toChinese(parts[i], numArray);
			if (i % 2 == 0) {
				if ("".equals(partChinese))
					beforeWanIsZero = true;
				else
					beforeWanIsZero = false;
			}

			if (i != 0) {
				if (i % 2 == 0)
					chineseStr = "亿" + chineseStr;
				else {
					if ("".equals(partChinese) && !beforeWanIsZero) // 如果“万”对应的 part 为 0，而“万”下面一级不为 0，则不加“万”，而加“零”
						chineseStr = "零" + chineseStr;
					else {
						if (parts[i - 1] < 1000 && parts[i - 1] > 0) // 如果"万"的部分不为 0, 而"万"前面的部分小于 1000 大于 0， 则万后面应该跟“零”
							chineseStr = "零" + chineseStr;
						chineseStr = "万" + chineseStr;
					}
				}
			}
			chineseStr = partChinese + chineseStr;
		}

		if ("".equals(chineseStr)) // 整数部分为 0, 则表达为"零"
			chineseStr = numArray[0];
		else if (negative) // 整数部分不为 0
			chineseStr = "负" + chineseStr;

		chineseStr = chineseStr + "";

		if (numFen == 0 && numJiao == 0) {
			chineseStr = chineseStr + "";
		} else if (numFen == 0) {
			chineseStr = chineseStr + "点" + numArray[numJiao] + "";
		} else { // “分”数不为 0
			if (numJiao == 0)
				chineseStr = chineseStr + "点零" + numArray[numFen] + "";
			else
				chineseStr = chineseStr + "点" + numArray[numJiao] + numArray[numFen] + "";
		}

		return chineseStr;

	}