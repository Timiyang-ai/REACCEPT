@Override
        public boolean equals(Object an) {
            if (an == null) {
                return false;
            }
            return an.getClass() == this.getClass()
                    && name.equalsIgnoreCase(((Name) an).name);
        }