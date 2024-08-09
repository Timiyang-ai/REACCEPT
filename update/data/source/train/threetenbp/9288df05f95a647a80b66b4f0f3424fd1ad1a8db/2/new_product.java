public Long getParsed(TemporalField field) {
        for (Object obj : currentParsed().parsed) {
            if (obj instanceof FieldValue) {
                FieldValue fv = (FieldValue) obj;
                if (fv.field.equals(field)) {
                    return fv.value;
                }
            }
        }
        return null;
    }