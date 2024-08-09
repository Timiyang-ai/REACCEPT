public static long getPresetReminder(long currentReminder) {
		long now = Calendar.getInstance().getTimeInMillis();
		return now > currentReminder ? getNextMinute() : currentReminder;
	}