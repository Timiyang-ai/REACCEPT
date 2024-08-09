public static Timestamp valueOf(String s) throws IllegalArgumentException {
        if (s == null) {
            // sql.3=Argument cannot be null
            throw new IllegalArgumentException(Messages.getString("sql.3")); //$NON-NLS-1$
        }

        // omit trailing whitespaces
        s = s.trim();
        if (!Pattern.matches(TIME_FORMAT_REGEX, s)) {
            throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //$NON-NLS-1$
        ParsePosition pp = new ParsePosition(0);

        /*
         * First parse out the yyyy-MM-dd HH:mm:ss component of the String into
         * a Date object using the SimpleDateFormat. This should stop after the
         * seconds value, according to the definition of SimpleDateFormat.parse,
         * with the ParsePosition indicating the index of the "." which should
         * precede the nanoseconds value
         */
        Date theDate;
        try {
            theDate = df.parse(s, pp);
        } catch (Exception e) {
            throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
        }

        if (theDate == null) {
            throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
        }

        /*
         * If we get here, the Date part of the string was OK - now for the
         * nanoseconds value. Strictly, this requires the remaining part of the
         * String to look like ".nnnnnnnnn". However, we accept anything with a
         * '.' followed by 1 to 9 digits - we also accept nothing (no fractions
         * of a second). Anything else is interpreted as incorrect format which
         * will generate an IllegalArgumentException
         */
        int position = pp.getIndex();
        int remaining = s.length() - position;
        int theNanos;

        if (remaining == 0) {
            // First, allow for the case where no fraction of a second is given:
            theNanos = 0;
        } else {
            /*
             * Case where fraction of a second is specified: Require 1 character
             * plus the "." in the remaining part of the string...
             */
            if ((s.length() - position) < ".n".length()) { //$NON-NLS-1$
                throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
            }

            /*
             * If we're strict, we should not allow any EXTRA characters after
             * the 9 digits
             */
            if ((s.length() - position) > ".nnnnnnnnn".length()) { //$NON-NLS-1$
                throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
            }

            // Require the next character to be a "."
            if (s.charAt(position) != '.') {
                // sql.4=Bad input string format: expected '.' not {0}
                throw new NumberFormatException(Messages.getString(
                        "sql.4", s.charAt(position))); //$NON-NLS-1$
            }
            // Get the length of the number string - need to account for the '.'
            int nanoLength = s.length() - position - 1;

            // Get the 9 characters following the "." as an integer
            String theNanoString = s.substring(position + 1, position + 1
                    + nanoLength);
            /*
             * We must adjust for the cases where the nanos String was not 9
             * characters long by padding out with zeros
             */
            theNanoString = theNanoString + "000000000"; //$NON-NLS-1$
            theNanoString = theNanoString.substring(0, 9);

            try {
                theNanos = Integer.parseInt(theNanoString);
            } catch (Exception e) {
                // If we get here, the string was not a number
                throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
            }
        }

        if (theNanos < 0 || theNanos > 999999999) {
            throw new IllegalArgumentException(Messages.getString("sql.2")); //$NON-NLS-1$
        }

        Timestamp theTimestamp = new Timestamp(theDate.getTime());
        theTimestamp.setNanos(theNanos);

        return theTimestamp;
    }