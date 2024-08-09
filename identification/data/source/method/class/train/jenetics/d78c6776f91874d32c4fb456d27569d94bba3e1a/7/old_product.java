@Override
	public boolean isValid() {
		byte[] check = new byte[length()/8 + 1];
		Arrays.fill(check, (byte)0);
		
		boolean valid = true;
		for (int i = 0; i < length() && valid; ++i) {
			final int value = _genes.get(i).intValue();
			if (value >= 0 && value < length()) {
				if (BitUtils.getBit(check, value)) {
					valid = false;
				} else {
					BitUtils.setBit(check, value, true);
				}
			} else {
				valid = false;
			}
		}
		return valid;
	}