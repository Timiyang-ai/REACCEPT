public static long getPresetReminder(Long currentReminder) {
		long now = Calendar.getInstance().getTimeInMillis();
		return currentReminder != null && currentReminder > now ? currentReminder : getNextMinute();
	}