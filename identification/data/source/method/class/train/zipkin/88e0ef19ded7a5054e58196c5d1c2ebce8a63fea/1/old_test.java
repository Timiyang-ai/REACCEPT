@Test public void ipv6() {
    assertThat(writeIpV6("1:2:3:4:5:6:7:8"))
        .isEqualTo("1:2:3:4:5:6:7:8");
    assertThat(writeIpV6("2001:0:0:4:0000:0:0:8"))
        .isEqualTo("2001:0:0:4::8");
    assertThat(writeIpV6("2001:0:0:4:5:6:7:8"))
        .isEqualTo("2001::4:5:6:7:8");
    assertThat(writeIpV6("2001:0:3:4:5:6:7:8"))
        .isEqualTo("2001::3:4:5:6:7:8");
    assertThat(writeIpV6("0:0:3:0:0:0:0:ffff"))
        .isEqualTo("0:0:3::ffff");
    assertThat(writeIpV6("0:0:0:4:0:0:0:ffff"))
        .isEqualTo("::4:0:0:0:ffff");
    assertThat(writeIpV6("0:0:0:0:5:0:0:ffff"))
        .isEqualTo("::5:0:0:ffff");
    assertThat(writeIpV6("1:0:0:4:0:0:7:8"))
        .isEqualTo("1::4:0:0:7:8");
    assertThat(writeIpV6("0:0:0:0:0:0:0:0"))
        .isEqualTo("::");
    assertThat(writeIpV6("0:0:0:0:0:0:0:1"))
        .isEqualTo("::1");
    assertThat(writeIpV6("2001:0658:022a:cafe::"))
        .isEqualTo("2001:658:22a:cafe::");
    assertThat(writeIpV6("::1.2.3.4"))
        .isEqualTo("::102:304");
  }