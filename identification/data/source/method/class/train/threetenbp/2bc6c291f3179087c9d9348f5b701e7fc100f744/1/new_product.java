public void print(Calendrical calendrical, Appendable appendable, DateTimeFormatSymbols symbols) throws IOException {
        ZoneOffset offset = calendrical.get(ZoneOffset.rule());
        if (offset == null) {
            throw new CalendricalPrintException("Unable to print ZoneOffset");
        }
        int totalSecs = offset.getAmountSeconds();
        if (totalSecs == 0) {
            appendable.append(noOffsetText);
        } else if (type == 4 || (type == 2 && offset.getSecondsField() == 0)) {
            appendable.append(offset.getID());
        } else {
            int absHours = Math.abs(offset.getHoursField());
            int absMinutes = Math.abs(offset.getMinutesField());
            int absSeconds = Math.abs(offset.getSecondsField());
            appendable.append(totalSecs < 0 ? "-" : "+")
                .append((char) (absHours / 10 + '0')).append((char) (absHours % 10 + '0'));
            if (type >= 1) {
                appendable.append((type % 2) == 0 ? ":" : "")
                    .append((char) (absMinutes / 10 + '0')).append((char) (absMinutes % 10 + '0'));
                if (type >= 5 || (type >= 3 && absSeconds > 0)) {
                    appendable.append((type % 2) == 0 ? ":" : "")
                        .append((char) (absSeconds / 10 + '0')).append((char) (absSeconds % 10 + '0'));
                }
            }
        }
    }