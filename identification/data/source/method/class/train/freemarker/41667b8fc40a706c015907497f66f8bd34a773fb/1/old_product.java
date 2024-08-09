public static String dateToISO8601String(
            Date date,
            boolean datePart, boolean timePart, boolean offsetPart,
            int accuracy,
            TimeZone timeZone,
            DateToISO8601CalendarFactory calendarFactory) {
        if (!timePart && offsetPart) {
            throw new IllegalArgumentException(
                    "ISO 8601:2004 doesn't specify any formats where the "
                    + "offset is shown but the time isn't.");
        }
        
        if (timeZone == null) {
            timeZone = UTC;
        }
        
        GregorianCalendar cal = calendarFactory.get(timeZone, date);

        int maxLength;
        if (!timePart) {
            maxLength = 10;  // YYYY-MM-DD
        } else {
            if (!datePart) {
                maxLength = 18;  // HH:MM:SS.mmm+00:00
            } else {
                maxLength = 10 + 1 + 18;
            }
        }
        char[] res = new char[maxLength];
        int dstIdx = 0;
        
        if (datePart) {
            int x = cal.get(Calendar.YEAR);
            if (x > 0 && cal.get(Calendar.ERA) == GregorianCalendar.BC) {
                x = -x + 1;
            }
            if (x >= 0 && x < 9999) {
                res[dstIdx++] = (char) ('0' + x / 1000);
                res[dstIdx++] = (char) ('0' + x % 1000 / 100);
                res[dstIdx++] = (char) ('0' + x % 100 / 10);
                res[dstIdx++] = (char) ('0' + x % 10);
            } else {
                String yearString = String.valueOf(x);
                
                // Re-allocate buffer:
                maxLength = maxLength - 4 + yearString.length();
                res = new char[maxLength];
                
                for (int i = 0; i < yearString.length(); i++) {
                    res[dstIdx++] = yearString.charAt(i);
                }
            }
    
            res[dstIdx++] = '-';
            
            x = cal.get(Calendar.MONTH) + 1;
            dstIdx = append00(res, dstIdx, x);
    
            res[dstIdx++] = '-';
            
            x = cal.get(Calendar.DAY_OF_MONTH);
            dstIdx = append00(res, dstIdx, x);

            if (timePart) {
                res[dstIdx++] = 'T';
            }
        }

        if (timePart) {
            int x = cal.get(Calendar.HOUR_OF_DAY);
            dstIdx = append00(res, dstIdx, x);
    
            if (accuracy >= ACCURACY_MINUTES) {
                res[dstIdx++] = ':';
        
                x = cal.get(Calendar.MINUTE);
                dstIdx = append00(res, dstIdx, x);
        
                if (accuracy >= ACCURACY_SECONDS) {
                    res[dstIdx++] = ':';
            
                    x = cal.get(Calendar.SECOND);
                    dstIdx = append00(res, dstIdx, x);
            
                    if (accuracy >= ACCURACY_MILLISECONDS) {
                        x = cal.get(Calendar.MILLISECOND);
                        if (x != 0) {
                            if (x > 999) {
                                // Shouldn't ever happen...
                                throw new RuntimeException(
                                        "Calendar.MILLISECOND > 999");
                            }
                            res[dstIdx++] = '.';
                            do {
                                res[dstIdx++] = (char) ('0' + (x / 100));
                                x = x % 100 * 10;
                            } while (x != 0);
                        }
                    }
                }
            }
        }

        if (offsetPart) {
            if (timeZone == UTC) {
                res[dstIdx++] = 'Z';
            } else {
                int dt = TIME_ZONE_OFFSET_CALCULATOR.getOffset(timeZone, date);
                boolean positive;
                if (dt < 0) {
                    positive = false;
                    dt = -dt;
                } else {
                    positive = true;
                }
                
                dt /= 1000;
                int offS = dt % 60;
                dt /= 60;
                int offM = dt % 60;
                dt /= 60;
                int offH = dt;
                
                if (offS == 0 && offM == 0 && offH == 0) {
                    res[dstIdx++] = 'Z';
                } else {
                    res[dstIdx++] = positive ? '+' : '-';
                    dstIdx = append00(res, dstIdx, offH);
                    res[dstIdx++] = ':';
                    dstIdx = append00(res, dstIdx, offM);
                    if (offS != 0) {
                        res[dstIdx++] = ':';
                        dstIdx = append00(res, dstIdx, offS);
                    }
                }
            }
        }
        
        return new String(res, 0, dstIdx);
    }