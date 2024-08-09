public static void insert(String key, String keyword, String nature, int freq) {
		Forest dic = get(key);

		if (dic == null) {
			dic = putIfAbsent(DEFAULT, DEFAULT, new Forest());
		}

		String[] paramers = new String[2];
		paramers[0] = nature;
		paramers[1] = String.valueOf(freq);
		Value value = new Value(keyword, paramers);
		Library.insertWord(dic, value);
	}