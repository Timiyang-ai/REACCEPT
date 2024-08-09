@Deprecated
    public static long parse(String s) {
        int year = Integer.MIN_VALUE;
        int mon = -1;
        int mday = -1;
        int hour = -1;
        int min = -1;
        int sec = -1;
        int millis = -1;
        int c = -1;
        int i = 0;
        int n = -1;
        int wst = -1;
        int tzoffset = -1;
        int prevc = 0;
    syntax:
        {
            if (s == null)
                break syntax;
            int limit = s.length();
            while (i < limit) {
                c = s.charAt(i);
                i++;
                if (c <= ' ' || c == ',')
                    continue;
                if (c == '(') { // skip comments
                    int depth = 1;
                    while (i < limit) {
                        c = s.charAt(i);
                        i++;
                        if (c == '(') depth++;
                        else if (c == ')')
                            if (--depth <= 0)
                                break;
                    }
                    continue;
                }
                if ('0' <= c && c <= '9') {
                    n = c - '0';
                    while (i < limit && '0' <= (c = s.charAt(i)) && c <= '9') {
                        n = (n * 10) + (c - '0');
                        i++;
                    }
                    if (prevc == '+' || prevc == '-' && year != Integer.MIN_VALUE) {
                        if (tzoffset != 0 && tzoffset != -1)
                            break syntax;

                        // timezone offset
                        if (n < 24) {
                            n = n * 60; // EG. "GMT-3"

                            // Support for Timezones of the form GMT-3:30. We look for an ':" and
                            // parse the number following it as loosely as the original hours
                            // section (i.e, no range or validity checks).
                            int minutesPart = 0;
                            if (i < limit && (s.charAt(i) == ':')) {
                                i++;
                                while (i < limit && '0' <= (c = s.charAt(i)) && c <= '9') {
                                    minutesPart = (minutesPart * 10) + (c - '0');
                                    i++;
                                }
                            }

                            n += minutesPart;
                        } else {
                            n = (n % 100) + ((n / 100) * 60); // eg "GMT-0430"
                        }

                        if (prevc == '+')   // plus means east of GMT
                            n = -n;

                        tzoffset = n;
                    } else if (n >= 70)
                        if (year != Integer.MIN_VALUE)
                            break syntax;
                        else if (c <= ' ' || c == ',' || c == '/' || i >= limit)
                            // year = n < 1900 ? n : n - 1900;
                            year = n;
                        else
                            break syntax;
                    else if (c == ':')
                        if (hour < 0)
                            hour = (byte) n;
                        else if (min < 0)
                            min = (byte) n;
                        else
                            break syntax;
                    else if (c == '/')
                        if (mon < 0)
                            mon = (byte) (n - 1);
                        else if (mday < 0)
                            mday = (byte) n;
                        else
                            break syntax;
                    else if (i < limit && c != ',' && c > ' ' && c != '-')
                        break syntax;
                    else if (hour >= 0 && min < 0)
                        min = (byte) n;
                    else if (min >= 0 && sec < 0)
                        sec = (byte) n;
                    else if (mday < 0)
                        mday = (byte) n;
                    // Handle two-digit years < 70 (70-99 handled above).
                    else if (year == Integer.MIN_VALUE && mon >= 0 && mday >= 0)
                        year = n;
                    else
                        break syntax;
                    prevc = 0;
                } else if (c == '/' || c == ':' || c == '+' || c == '-')
                    prevc = c;
                else {
                    int st = i - 1;
                    while (i < limit) {
                        c = s.charAt(i);
                        if (!('A' <= c && c <= 'Z' || 'a' <= c && c <= 'z'))
                            break;
                        i++;
                    }
                    if (i <= st + 1)
                        break syntax;
                    int k;
                    for (k = wtb.length; --k >= 0;)
                        if (wtb[k].regionMatches(true, 0, s, st, i - st)) {
                            int action = ttb[k];
                            if (action != 0) {
                                if (action == 1) {  // pm
                                    if (hour > 12 || hour < 1)
                                        break syntax;
                                    else if (hour < 12)
                                        hour += 12;
                                } else if (action == 14) {  // am
                                    if (hour > 12 || hour < 1)
                                        break syntax;
                                    else if (hour == 12)
                                        hour = 0;
                                } else if (action <= 13) {  // month!
                                    if (mon < 0)
                                        mon = (byte) (action - 2);
                                    else
                                        break syntax;
                                } else {
                                    tzoffset = action - 10000;
                                }
                            }
                            break;
                        }
                    if (k < 0)
                        break syntax;
                    prevc = 0;
                }
            }
            if (year == Integer.MIN_VALUE || mon < 0 || mday < 0)
                break syntax;
            // Parse 2-digit years within the correct default century.
            if (year < 100) {
                synchronized (Date.class) {
                    if (defaultCenturyStart == 0) {
                        defaultCenturyStart = gcal.getCalendarDate().getYear() - 80;
                    }
                }
                year += (defaultCenturyStart / 100) * 100;
                if (year < defaultCenturyStart) year += 100;
            }
            if (sec < 0)
                sec = 0;
            if (min < 0)
                min = 0;
            if (hour < 0)
                hour = 0;
            BaseCalendar cal = getCalendarSystem(year);
            if (tzoffset == -1)  { // no time zone specified, have to use local
                BaseCalendar.Date ldate = (BaseCalendar.Date) cal.newCalendarDate(TimeZone.getDefaultRef());
                ldate.setDate(year, mon + 1, mday);
                ldate.setTimeOfDay(hour, min, sec, 0);
                return cal.getTime(ldate);
            }
            BaseCalendar.Date udate = (BaseCalendar.Date) cal.newCalendarDate(null); // no time zone
            udate.setDate(year, mon + 1, mday);
            udate.setTimeOfDay(hour, min, sec, 0);
            return cal.getTime(udate) + tzoffset * (60 * 1000);
        }
        // syntax error
        throw new IllegalArgumentException();
    }