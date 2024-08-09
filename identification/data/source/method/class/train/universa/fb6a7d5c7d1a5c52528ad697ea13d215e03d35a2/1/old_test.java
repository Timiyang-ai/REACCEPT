    @Test
    public void updateFields() throws Exception {
        ZonedDateTime zdt = ZonedDateTime.now().plusHours(1);
        String field1 = "definition.issuer";
        String value1 = "<definition.issuer>\n" +
                "    <SimpleRole>\n" +
                "      <keys isArray=\"true\">\n" +
                "        <item>\n" +
                "          <KeyRecord>\n" +
                "            <name>Universa</name>\n" +
                "            <key>\n" +
                "              <RSAPublicKey>\n" +
                "                <packed>\n" +
                "                  <binary>\n" +
                "                    <base64>HggcAQABxAACzHE9ibWlnK4RzpgFIB4jIg3WcXZSKXNAqOTYUtGXY03xJSwpqE+y/HbqqE0W\n" +
                "smcAt5a0F5H7bz87Uy8Me1UdIDcOJgP8HMF2M0I/kkT6d59ZhYH/TlpDcpLvnJWElZAfOyta\n" +
                "ICE01bkOkf6Mz5egpToDEEPZH/RXigj9wkSXkk43WZSxVY5f2zaVmibUZ9VLoJlmjNTZ+utJ\n" +
                "UZi66iu9e0SXupOr/+BJL1Gm595w32Fd0141kBvAHYDHz2K3x4m1oFAcElJ83ahSl1u85/na\n" +
                "Iaf2yuxiQNz3uFMTn0IpULCMvLMvmE+L9io7+KWXld2usujMXI1ycDRw85h6IJlPcKHVQKnJ\n" +
                "/4wNBUveBDLFLlOcMpCzWlO/D7M2IyNa8XEvwPaFJlN1UN/9eVpaRUBEfDq6zi+RC8MaVWzF\n" +
                "bNi913suY0Q8F7ejKR6aQvQPuNN6bK6iRYZchxe/FwWIXOr0C0yA3NFgxKLiKZjkd5eJ84GL\n" +
                "y+iD00Rzjom+GG4FDQKr2HxYZDdDuLE4PEpYSzEB/8LyIqeM7dSyaHFTBII/sLuFru6ffoKx\n" +
                "BNk/cwAGZqOwD3fkJjNq1R3h6QylWXI/cSO9yRnRMmMBJwalMexOc3/kPEEdfjH/GcJU0Mw6\n" +
                "DgoY8QgfaNwXcFbBUvf3TwZ5Mysf21OLHH13g8gzREm+h8c=</base64>\n" +
                "                  </binary>\n" +
                "                </packed>\n" +
                "              </RSAPublicKey>\n" +
                "            </key>\n" +
                "          </KeyRecord>\n" +
                "        </item>\n" +
                "      </keys>\n" +
                "      <name>issuer</name>\n" +
                "    </SimpleRole>\n" +
                "  </definition.issuer>";
        String field2 = "definition.expires_at";
        String value2 = "<definition.expires__at>\n" +
                "       <unixtime>" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss [XXX]").format(zdt) + "</unixtime>\n" +
                "</definition.expires__at>";
        callMain(
                "-e", basePath + "contract_to_export.unicon",
                "-set", field1, "-value", value1,
                "-set", field2, "-value", value2);
        System.out.println(output);
//        assert(output.indexOf("update field " + field1 + " ok") >= 0);
        assertTrue (output.indexOf("update field " + field2 + " ok") >= 0);
        assertTrue (output.indexOf("contract expires at " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(zdt)) >= 0);
        assertEquals(0, errors.size());
    }