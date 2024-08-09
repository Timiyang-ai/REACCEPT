@Override
        public String toString() {
            try {
                return new String(name, "ISO-8859-1");
            } catch (UnsupportedEncodingException iee) {
                throw new InternalError(iee.getLocalizedMessage());
            }
        }