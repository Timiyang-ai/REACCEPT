public static boolean isEmpty(final Object array) {
		if(null == array) {
			return true;
		}else if(false == isArray(array)) {
			//非数组，理解为此对象为数组的第一个元素
			return false;
		}else {
			return 0 == Array.getLength(array);
		}
	}