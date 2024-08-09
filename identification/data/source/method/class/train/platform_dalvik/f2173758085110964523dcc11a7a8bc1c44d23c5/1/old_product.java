protected Object readResolve() throws InvalidObjectException {
            if (this.getClass() != Attribute.class) {
                // text.0C=cannot resolve subclasses
                throw new InvalidObjectException(Messages.getString("text.0C")); //$NON-NLS-1$
            }
            if (this.name.equals(INPUT_METHOD_SEGMENT.name)) {
                return INPUT_METHOD_SEGMENT;
            }
            if (this.name.equals(LANGUAGE.name)) {
                return LANGUAGE;
            }
            if (this.name.equals(READING.name)) {
                return READING;
            }
            // text.02=Unknown attribute
            throw new InvalidObjectException(Messages.getString("text.02")); //$NON-NLS-1$
        }