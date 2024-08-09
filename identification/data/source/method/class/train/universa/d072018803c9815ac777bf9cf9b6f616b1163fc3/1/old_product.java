@Override
    @NonNull
    public Collection<AbstractKey> findKey(KeyInfo keyInfo) {
        final ArrayList<AbstractKey> result = new ArrayList<>();
        for( AbstractKey k: keys) {
            final KeyInfo ki = k.keyInfo;
            if( ki.matchType(keyInfo) ) {
                if( ki.matchTag(keyInfo) )
                    result.add(0, k);
                else
                    result.add(k);
            }
        }
        return result;
    }