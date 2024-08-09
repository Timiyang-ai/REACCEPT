@SuppressWarnings("deprecation")
	public static Date resolveDateTime(int date, int time) {
		
		int month = ((date & 0xE000) >> 12)+((date & 0x80) >> 7);
		int day = (date & 0x1F00) >> 8;
		int year = (date & 0x0F) + 2000;
	
		int hours = (int)(time * 0.5);
		int minutes = (int)(60 * ((time * 0.5)-hours));
		
		return new Date(year, month, day, hours, minutes);
	}