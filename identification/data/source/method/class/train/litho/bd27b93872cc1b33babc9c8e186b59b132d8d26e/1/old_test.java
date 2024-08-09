  @Test
  public void addRemainingCompletions() {
    testHelper.runInReadAction(
        project -> {
          TestCompletionResultSet mutate = new TestCompletionResultSet();
          List<String> namesToReplace = new ArrayList<>();
          namesToReplace.add("one");
          namesToReplace.add("other");

          new ReplacingConsumer(namesToReplace, mutate, "Test").addRemainingCompletions(project);

          // Creates Completions without consumption
          assertThat(mutate.elements).hasSize(2);
          assertThat(mutate.elements.get(0).getLookupString()).isEqualTo("other");
          assertThat(mutate.elements.get(1).getLookupString()).isEqualTo("one");
        });
  }