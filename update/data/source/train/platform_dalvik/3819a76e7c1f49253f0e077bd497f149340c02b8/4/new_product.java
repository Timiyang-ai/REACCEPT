@Override
        public int hashCode() {
            if (hashCode == 0) {
                int hash = 0, multiplier = 1;
                for (int i = name.length - 1; i >= 0; i--) {
                    // 'A' & 0xDF == 'a' & 0xDF, ..., 'Z' & 0xDF == 'z' & 0xDF
                    hash += (name[i] & 0xDF) * multiplier;
                    int shifted = multiplier << 5;
                    multiplier = shifted - multiplier;
                }
                hashCode = hash;
            }
            return hashCode;
        }