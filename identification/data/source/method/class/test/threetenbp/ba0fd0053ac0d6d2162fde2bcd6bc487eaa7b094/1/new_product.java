public void print(Calendrical calendrical, Appendable appendable) {
        DateTimeFormatter.checkNotNull(calendrical, "Calendrical must not be null");
        DateTimeFormatter.checkNotNull(appendable, "Appendable must not be null");
        try {
            printerParser.print(calendrical, appendable, symbols);
        } catch (UnsupportedRuleException ex) {
            throw new CalendricalPrintFieldException(ex);
        } catch (IOException ex) {
            throw new CalendricalPrintException(ex.getMessage(), ex);
        }
    }