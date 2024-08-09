public DateTimeFormatterBuilder appendZoneText(TextStyle textStyle) {
        // TODO: parsing of zone text?
//        * During parsing, either the textual zone name, the zone ID or the offset
//        * is accepted.
//        * If the zone cannot be parsed then an exception is thrown unless the
//        * section of the formatter is optional.
        appendInternal(new ZoneTextPrinterParser(textStyle));
        return this;
    }