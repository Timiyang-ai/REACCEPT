public static InternetDomainName validateHostName(String name) throws EppException {
    checkArgumentNotNull(name, "Must specify host name to validate");
    if (name.length() > 253) {
      throw new HostNameTooLongException();
    }
    String hostNameLowerCase = Ascii.toLowerCase(name);
    if (!name.equals(hostNameLowerCase)) {
      throw new HostNameNotLowerCaseException(hostNameLowerCase);
    }
    try {
      String hostNamePunyCoded = Idn.toASCII(name);
      if (!name.equals(hostNamePunyCoded)) {
        throw new HostNameNotPunyCodedException(hostNamePunyCoded);
      }
      InternetDomainName hostName = InternetDomainName.from(name);
      if (!name.equals(hostName.toString())) {
        throw new HostNameNotNormalizedException(hostName.toString());
      }
      // The effective TLD is, in order of preference, the registry suffix, if the TLD is a real TLD
      // published in the public suffix list (https://publicsuffix.org/, note that a registry suffix
      // is in the "ICANN DOMAINS" in that list); or a TLD managed by Nomulus (in-bailiwick), found
      // by #findTldForName; or just the last part of a domain name.
      InternetDomainName effectiveTld =
          hostName.isUnderRegistrySuffix()
              ? hostName.registrySuffix()
              : findTldForName(hostName).orElse(InternetDomainName.from("invalid"));
      // Checks whether a hostname is deep enough. Technically a host can be just one level beneath
      // the effective TLD (e.g. example.com) but we require by policy that it has to be at least
      // one part beyond that (e.g. ns1.example.com).
      if (hostName.parts().size() < effectiveTld.parts().size() + 2) {
        throw new HostNameTooShallowException();
      }
      return hostName;
    } catch (IllegalArgumentException e) {
      throw new InvalidHostNameException();
    }
  }