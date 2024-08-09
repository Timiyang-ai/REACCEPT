public static String getGroupEmailAddressForContactType(
      String clientId,
      RegistrarContact.Type type,
      String publicDomainName) {
    // Take the registrar's clientId, make it lowercase, and remove all characters that aren't
    // alphanumeric, hyphens, or underscores.
    return String.format(
        "%s-%s-contacts@%s", normalizeClientId(clientId), type.getDisplayName(), publicDomainName);
  }