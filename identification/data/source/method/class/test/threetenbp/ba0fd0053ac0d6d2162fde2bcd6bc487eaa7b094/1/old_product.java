public void print(Calendrical calendrical, Appendable appendable) {
        DateTimeFormatter.checkNotNull(calendrical, "Calendrical must not be null");
        DateTimeFormatter.checkNotNull(appendable, "Appendable must not be null");
        try {
            printerParser.print(calendrical, appendable, symbols);
        } catch (UnsupportedRuleException ex) {
            throw new CalendricalFormatFieldException(ex);
        } catch (IOException ex) {
            throw new CalendricalFormatException(ex.getMessage(), ex);
        }
    }