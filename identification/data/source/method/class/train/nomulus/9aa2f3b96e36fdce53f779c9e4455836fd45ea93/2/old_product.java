static InternetDomainName validateHostName(String name) throws EppException {
    checkArgumentNotNull(name, "Must specify host name to validate");
    if (name.length() > 253) {
      throw new HostNameTooLongException();
    }
    String hostNameLowerCase = Ascii.toLowerCase(name);
    if (!name.equals(hostNameLowerCase)) {
      throw new HostNameNotLowerCaseException(hostNameLowerCase);
    }
    String hostNamePunyCoded = Idn.toASCII(name);
    if (!name.equals(hostNamePunyCoded)) {
      throw new HostNameNotPunyCodedException(hostNamePunyCoded);
    }
    try {
      InternetDomainName hostName = InternetDomainName.from(name);
      if (!name.equals(hostName.toString())) {
        throw new HostNameNotNormalizedException(hostName.toString());
      }
      // Checks whether a hostname is deep enough. Technically a host can be just one under a
      // public suffix (e.g. example.com) but we require by policy that it has to be at least one
      // part beyond that (e.g. ns1.example.com). The public suffix list includes all current
      // ccTlds, so this check requires 4+ parts if it's a ccTld that doesn't delegate second
      // level domains, such as .co.uk. But the list does not include new tlds, so in that case
      // we just ensure 3+ parts. In the particular case where our own tld has a '.' in it, we know
      // that there need to be 4 parts as well.
      if (hostName.isUnderPublicSuffix()) {
        if (hostName.parent().isUnderPublicSuffix()) {
          return hostName;
        }
      } else {
        // We need to know how many parts the hostname has beyond the public suffix, but we don't
        // know what the public suffix is. If the host is in bailiwick and we are hosting a
        // multipart "tld" like .co.uk the publix suffix might be 2 parts. Otherwise it's an
        // unrecognized tld that's not on the public suffix list, so assume the tld alone is the
        // public suffix.
        Optional<InternetDomainName> tldParsed = findTldForName(hostName);
        int suffixSize = tldParsed.isPresent() ? tldParsed.get().parts().size() : 1;
        if (hostName.parts().size() >= suffixSize + 2) {
          return hostName;
        }
      }
      throw new HostNameTooShallowException();
    } catch (IllegalArgumentException e) {
      throw new InvalidHostNameException();
    }
  }