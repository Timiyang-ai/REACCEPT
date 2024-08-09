public static AminoAcid getAminoAcidByName(String name) {
        if (codonTable == null) {
            initTable();
        }

        boolean found;
        for (AminoAcid aa : codonTable.values()) {
            found = aa.equalsByName(name);
            if (found) {
                return aa;
            }

        }

        return AminoAcid.NULL_AMINO_ACID;
    }