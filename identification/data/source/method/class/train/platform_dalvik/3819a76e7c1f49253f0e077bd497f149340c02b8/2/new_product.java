@Override
        public boolean equals(Object object) {
            if (object == null || object.getClass() != getClass()
                    || object.hashCode() != hashCode()) {
                return false;
            }

            return Util.equalsIgnoreCase(name, ((Name) object).name);
        }