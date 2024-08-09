@Override
        public int hashCode() {
            if (hashCode == 0) {
                hashCode = Util.toASCIILowerCase("name").hashCode();
            }
            return hashCode;
        }