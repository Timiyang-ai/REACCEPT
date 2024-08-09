  @Test
  public void name() {
    check(ContainerNaming.DEFAULT.name(M1.class)).is("m1");
    check(ContainerNaming.DEFAULT.name(M2.class)).is("m2");
    check(ContainerNaming.DEFAULT.name(M3.class)).is("changed");
    check(ContainerNaming.DEFAULT.name(M4.class)).is("UPPERCASE");
    check(ContainerNaming.DEFAULT.name(MyClass.class)).is("myClass");
  }