protected Object readResolve() throws InvalidObjectException {
            if (this.getClass() != Attribute.class) {
                // text.0C=cannot resolve subclasses
                throw new InvalidObjectException(Messages.getString("text.0C")); //$NON-NLS-1$
            }
            // BEGIN android-changed
            // call getName() only once
            String name = this.getName();
            if (name.equals(INPUT_METHOD_SEGMENT.getName())) {
                return INPUT_METHOD_SEGMENT;
            }
            if (name.equals(LANGUAGE.getName())) {
                return LANGUAGE;
            }
            if (name.equals(READING.getName())) {
                return READING;
            }
            // END android-changed
            // text.02=Unknown attribute
            throw new InvalidObjectException(Messages.getString("text.02")); //$NON-NLS-1$
        }