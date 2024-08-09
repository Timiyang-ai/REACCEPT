public static Date parse(String date, ParsePosition pos) throws ParseException {
        Exception fail;
        //noinspection TryWithIdenticalCatches
        try {
            int offset = pos.getIndex();

            // Extracts year.
            int year = parseInt(date, offset, offset += 4);
            if (checkOffset(date, offset, '-')) {
                offset += 1;
            }

            // Extracts month.
            int month = parseInt(date, offset, offset += 2);
            if (checkOffset(date, offset, '-')) {
                offset += 1;
            }

            // Extracts day.
            int day = parseInt(date, offset, offset += 2);
            // Default time value.
            int hour = 0;
            int minutes = 0;
            int seconds = 0;
            int milliseconds = 0; // Always use 0 otherwise returned date will include millis of current time.

            // If the value has no time component (and no time zone), we are done.
            boolean hasT = checkOffset(date, offset, 'T');

            if (!hasT && (date.length() <= offset)) {
                Calendar calendar = new GregorianCalendar(year, month - 1, day);

                pos.setIndex(offset);
                return calendar.getTime();
            }

            if (hasT) {

                // Extracts hours, minutes, seconds and milliseconds.
                hour = parseInt(date, offset += 1, offset += 2);
                if (checkOffset(date, offset, ':')) {
                    offset += 1;
                }

                minutes = parseInt(date, offset, offset += 2);
                if (checkOffset(date, offset, ':')) {
                    offset += 1;
                }
                // Second and milliseconds can be optional.
                if (date.length() > offset) {
                    char c = date.charAt(offset);
                    if (c != 'Z' && c != '+' && c != '-') {
                        seconds = parseInt(date, offset, offset += 2);
                        if (seconds > 59 && seconds < 63) {
                            seconds = 59; // Truncates up to 3 leap seconds.
                        }
                        // Milliseconds can be optional in the format.
                        if (checkOffset(date, offset, '.')) {
                            offset += 1;
                            int endOffset = indexOfNonDigit(date, offset + 1); // Assumes at least one digit.
                            int parseEndOffset = Math.min(endOffset, offset + 3); // Parses up to 3 digits.
                            int fraction = parseInt(date, offset, parseEndOffset);
                            // Compensates for "missing" digits.
                            switch (parseEndOffset - offset) { // Number of digits parsed.
                                case 2:
                                    milliseconds = fraction * 10;
                                    break;
                                case 1:
                                    milliseconds = fraction * 100;
                                    break;
                                default:
                                    milliseconds = fraction;
                            }
                            offset = endOffset;
                        }
                    }
                }
            }

            // Extracts timezone.
            if (date.length() <= offset) {
                throw new IllegalArgumentException("No time zone indicator");
            }

            TimeZone timezone;
            char timezoneIndicator = date.charAt(offset);

            if (timezoneIndicator == 'Z') {
                timezone = TIMEZONE_Z;
                offset += 1;
            } else if (timezoneIndicator == '+' || timezoneIndicator == '-') {
                String timezoneOffset = date.substring(offset);
                offset += timezoneOffset.length();
                // Convert 2-digit time zone designator to 4-digit designator
                if (timezoneOffset.length() == 3) {
                    timezoneOffset += "00";
                }
                // 18-Jun-2015, tatu: Minor simplification, skip offset of "+0000"/"+00:00"
                if ("+0000".equals(timezoneOffset) || "+00:00".equals(timezoneOffset)) {
                    timezone = TIMEZONE_Z;
                } else {
                    // 18-Jun-2015, tatu: Looks like offsets only work from GMT, not UTC...
                    //    not sure why, but that's the way it looks. Further, Javadocs for
                    //    `java.util.TimeZone` specifically instruct use of GMT as base for
                    //    custom timezones... odd.
                    String timezoneId = "GMT" + timezoneOffset;

                    timezone = TimeZone.getTimeZone(timezoneId);

                    String act = timezone.getID();
                    if (!act.equals(timezoneId)) {
                        /* 22-Jan-2015, tatu: Looks like canonical version has colons, but we may be given
                         *    one without. If so, don't sweat.
                         *   Yes, very inefficient. Hopefully not hit often.
                         *   If it becomes a perf problem, add 'loose' comparison instead.
                         */
                        String cleaned = act.replace(":", "");
                        if (!cleaned.equals(timezoneId)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + timezoneId + " given, resolves to "
                                    + timezone.getID());
                        }
                    }
                }
            } else {
                throw new IndexOutOfBoundsException("Invalid time zone indicator '" + timezoneIndicator + "'");
            }

            Calendar calendar = new GregorianCalendar(timezone);
            calendar.setLenient(false);
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minutes);
            calendar.set(Calendar.SECOND, seconds);
            calendar.set(Calendar.MILLISECOND, milliseconds);

            pos.setIndex(offset);
            return calendar.getTime();
            // If we get a ParseException it'll already have the right message/offset.
            // Other exception types can convert here.
        } catch (IndexOutOfBoundsException e) {
            fail = e;
        } catch (NumberFormatException e) {
            fail = e;
        } catch (IllegalArgumentException e) {
            fail = e;
        }
        String input = (date == null) ? null : ('"' + date + "'");
        String msg = fail.getMessage();
        if (msg == null || msg.isEmpty()) {
            msg = "(" + fail.getClass().getName() + ")";
        }
        ParseException ex = new ParseException("Failed to parse date [" + input + "]: " + msg, pos.getIndex());
        ex.initCause(fail);
        throw ex;
    }