public void print(Calendrical calendrical, Appendable appendable) {
        DateTimeFormatter.checkNotNull(calendrical, "Calendrical must not be null");
        DateTimeFormatter.checkNotNull(appendable, "Appendable must not be null");
        try {
            if (appendable instanceof StringBuilder) {
                printerParser.print(calendrical, (StringBuilder) appendable, symbols);
            } else {
                // buffer output to avoid writing to appendable in case of error
                StringBuilder buf = new StringBuilder(32);
                printerParser.print(calendrical, buf, symbols);
                appendable.append(buf);
            }
        } catch (UnsupportedRuleException ex) {
            throw new CalendricalPrintFieldException(ex);
        } catch (IOException ex) {
            throw new CalendricalPrintException(ex.getMessage(), ex);
        }
    }