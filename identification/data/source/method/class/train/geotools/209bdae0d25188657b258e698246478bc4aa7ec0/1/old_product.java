final IdentifiedObject createFromCodes(final IdentifiedObject object) throws FactoryException {
        final Set/*<String>*/ codes = getCodeCandidates(object);
        for (final Iterator it=codes.iterator(); it.hasNext();) {
            final String code = (String) it.next();
            IdentifiedObject candidate;
            try {
                candidate = getProxy().create(code);
            }
            catch (FactoryException e) {
                LOGGER.log( Level.FINEST, "Could not create '"+code+"':"+e );
                // Some object cannot be created properly.
                continue;
            }
            catch (Exception problemCode ){
                LOGGER.log( Level.FINEST, "Could not create '"+code+"':"+problemCode, problemCode );
                continue;
            }

            candidate = deriveEquivalent(candidate, object);
            if (candidate != null) {
                return candidate;
            }
        }
        return null;
    }