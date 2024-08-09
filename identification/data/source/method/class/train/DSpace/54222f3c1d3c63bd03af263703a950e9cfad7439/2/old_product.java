@Override
    public boolean supports(String identifier)
    {
        try {
            DOI.formatIdentifier(identifier);
        } catch (IdentifierException e) {
            return false;
        } catch (IllegalArgumentException e)
        {
            return false;
        }
        return true;
    }