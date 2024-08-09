public static AminoAcid getAminoAcidByName(String name) {
        initAANameMap();

        AminoAcid aa = AANameMap.get(name);
        if (aa == null) {
            aa = AminoAcid.NULL_AMINO_ACID;
        }

        return aa;
    }