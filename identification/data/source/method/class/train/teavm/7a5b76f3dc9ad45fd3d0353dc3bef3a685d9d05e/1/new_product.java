@Override
        public int getTimezoneOffset(int year, int month, int day, int timeOfDayMillis) {
            JSDate d = TTimeZone.createJSDate(year, month, day, 0, 0, 0, 0);
            return (int)(offset + (isTimezoneDST((long)d.getTime())?ONE_HOUR:0));
        }