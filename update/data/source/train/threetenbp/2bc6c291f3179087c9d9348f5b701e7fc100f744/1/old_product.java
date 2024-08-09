public void print(Calendrical calendrical, Appendable appendable, DateTimeFormatSymbols symbols) throws IOException {
        ZoneOffset offset = calendrical.get(ZoneOffset.rule());
        if (offset == null) {
            throw new CalendricalPrintException("Unable to print ZoneOffset");
        }
        int totalSecs = offset.getAmountSeconds();
        if (totalSecs == 0) {
            appendable.append(utcText);
        } else if (includeColon && (allowSeconds || offset.getSecondsField() == 0)) {
            appendable.append(offset.getID());
        } else {
            int absHours = Math.abs(offset.getHoursField());
            int absMinutes = Math.abs(offset.getMinutesField());
            int absSeconds = Math.abs(offset.getSecondsField());
            appendable
                .append(totalSecs < 0 ? "-" : "+")
                .append((char) (absHours / 10 + '0')).append((char) (absHours % 10 + '0'))
                .append(includeColon ? ":" : "")
                .append((char) (absMinutes / 10 + '0')).append((char) (absMinutes % 10 + '0'));
            if (allowSeconds && absSeconds > 0) {
                appendable
                    .append(includeColon ? ":" : "")
                    .append((char) (absSeconds / 10 + '0')).append((char) (absSeconds % 10 + '0'));
            }
        }
    }