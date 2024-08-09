import difflib
import re

from util.llm.chain import Chain
from util.llm.prompt import Prompt
from util.react.environment import Environment
from util.react.knowledge import KnowledgeBase
from util.tool.diff import Diff
from util.tool.source import Source


class Updater:
    output_path = "output"

    # output_path = "output/gpt-3.5-turbo-0125/log"
    # output_path = "output/gpt-4-0125-preview/log"
    # output_path = "output/gpt-4-turbo-2024-04-09/log"
    # output_path = "output/gpt-4o-2024-05-13/log"
    # output_path = "output/temperature-0.5/log"
    # output_path = "output/temperature-1/log"
    # output_path = "output/top-p-0/log"
    # output_path = "output/top-p-0.5/log"
    # output_path = "output/best/log"

    source_path = "output"

    # source_path = "output/gpt-3.5-turbo-0125/source"
    # source_path = "output/gpt-4-0125-preview/source"
    # source_path = "output/gpt-4-turbo-2024-04-09/source"
    # source_path = "output/gpt-4o-2024-05-13/source"
    # source_path = "output/temperature-0.5/source"
    # source_path = "output/temperature-1/source"
    # source_path = "output/top-p-0/source"
    # source_path = "output/top-p-0.5/source"
    # source_path = "output/best/source"

    @staticmethod
    def parse(output):
        # return re.sub(
        #     '\n```(.|\n)*$', '', re.sub(
        #         '^(.|\n)*```java\n', '', re.sub(
        #             '\n</new_test>(.|\n)*$', '', re.sub(
        #                 '^(.|\n)*<new_test>\n', '', output))))
        # return re.sub(
        #     '\n```(.|\n)*$', '', re.sub(
        #         '^(.|\n)*```java\n', '', re.sub(
        #             '</new_test>(.|\n)*$', '', re.sub(
        #                 '^(.|\n)*<new_test>', '', output))))
        return re.sub(
            '\n```(.|\n)*$', '', re.sub(
                '^(.|\n)*```java\n', '', re.sub(
                    '</new_test>(.|\n)*$', '', re.sub(
                        '^(.|\n)*<new_test>', '', output)))).strip()

    @staticmethod
    def diff(last_test, generated_test):
        return '\n'.join(difflib.unified_diff(last_test.split(sep='\n'), generated_test.split(sep='\n'))) + "\n"

    @classmethod
    def update(cls, source, diff, environment):
        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("old product\n" + source.old_product + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("old test\n" + source.old_test + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("new product\n" + source.new_product + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("new test\n" + source.new_test + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("diff product\n" + diff.diff_product + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("diff test\n" + diff.diff_test + "\n")
        output_file.write(
            "----------------------------------------------------------------"
            "----------------------------------------------------------------\n")
        output_file.close()

        sample_diff = KnowledgeBase.retrieve(diff)

        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("sample diff product\n" + sample_diff.diff_product + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("sample diff test\n" + sample_diff.diff_test + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("coverage: " + str(environment.coverage) + "\n")
        output_file.write(
            "----------------------------------------------------------------"
            "----------------------------------------------------------------\n")
        output_file.close()

        generated_test = source.old_test

        count = 1

        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("now time: " + str(count) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.close()

        # output = Chain.conversation.predict(
        #     input=Prompt.human_template_diff.format(
        #         old_test=source.old_test,
        #         diff_product=diff.diff_product).content)
        # output = Chain.conversation.predict(
        #     input=Prompt.human_template_source.format(
        #         old_product=source.old_product,
        #         old_test=source.old_test,
        #         new_product=source.new_product).content)
        output = Chain.conversation.predict(
            input=Prompt.human_template_diff_sample.format(
                old_test=source.old_test,
                diff_product=diff.diff_product,
                sample_diff_product=sample_diff.diff_product,
                sample_diff_test=sample_diff.diff_test).content)
        # output = Chain.conversation.predict(
        #     input=Prompt.human_template_source_sample.format(
        #         old_product=source.old_product,
        #         old_test=source.old_test,
        #         new_product=source.new_product,
        #         sample_diff_product=sample_diff.diff_product,
        #         sample_diff_test=sample_diff.diff_test).content)

        last_test = generated_test
        generated_test = cls.parse(output)

        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("llm output\n" + output + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("generated test\n" + generated_test + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("last generated diff test\n" + cls.diff(last_test, generated_test) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.close()

        environment.update_method(last_test, generated_test)
        state, description, message = environment.test()

        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("state: " + str(state) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("description: " + description + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("message\n" + message + "\n")
        output_file.write(
            "----------------------------------------------------------------"
            "----------------------------------------------------------------\n")
        output_file.close()

        count_buffer = count
        generated_test_buffer = generated_test
        state_buffer = state
        description_buffer = description

        while state != 3:
            count += 1

            output_file = open(
                "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
                mode='a',
                encoding="utf-8")
            output_file.write("now time: " + str(count) + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.close()

            if state == 0:
                output = Chain.conversation.predict(
                    input=Prompt.human_template_compile.format(error=message).content)
            elif state == 1:
                output = Chain.conversation.predict(
                    input=Prompt.human_template_unit_test.format(failure=message).content)
            elif state == 2:
                output = Chain.conversation.predict(
                    input=Prompt.human_template_cover_test.format(element=message).content)

            last_test = generated_test
            generated_test = cls.parse(output)

            output_file = open(
                "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
                mode='a',
                encoding="utf-8")
            output_file.write("llm output\n" + output + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.write("generated test\n" + generated_test + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.write("last generated diff test\n" + cls.diff(last_test, generated_test) + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.close()

            environment.update_method(last_test, generated_test)
            state, description, message = environment.test()

            output_file = open(
                "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
                mode='a',
                encoding="utf-8")
            output_file.write("state: " + str(state) + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.write("description: " + description + "\n")
            output_file.write("----------------------------------------------------------------\n")
            output_file.write("message\n" + message + "\n")
            output_file.write(
                "----------------------------------------------------------------"
                "----------------------------------------------------------------\n")
            output_file.close()

            if state_buffer < state:
                count_buffer = count
                generated_test_buffer = generated_test
                state_buffer = state
                description_buffer = description

            # if count >= 16:
            if count >= 8:
                break

        output_file = open(
            "/".join([cls.output_path, "-".join([source.project, source.commit, source.number]) + ".txt"]),
            mode='a',
            encoding="utf-8")
        output_file.write("total times: " + str(count_buffer) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("generated test\n" + generated_test_buffer + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("old generated diff test\n" + cls.diff(source.old_test, generated_test_buffer) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("generated new diff test\n" + cls.diff(generated_test_buffer, source.new_test) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("state: " + str(state_buffer) + "\n")
        output_file.write("----------------------------------------------------------------\n")
        output_file.write("description: " + description_buffer + "\n")
        output_file.write(
            "----------------------------------------------------------------"
            "----------------------------------------------------------------\n")
        output_file.close()

        return generated_test_buffer


if __name__ == "__main__":
    # test
    project = "basex"
    commit = "0b2ef31462791b3f8266504ae39274f0b3ed6149"
    number = "1"

    module_product = "basex-core"
    module_test = "basex-core"
    package_product = "org/basex/query/func"
    package_test = "org/basex/query/func"
    class_product = "FNDb"
    class_test = "FNDbTest"

    source_test = "src/test/java"
    target_test = "target/test-classes"
    unit = "target/surefire-reports"
    cover = "target/site/jacoco"

    # project = "basex"
    # commit = "0b2ef31462791b3f8266504ae39274f0b3ed6149"
    # number = "2"
    #
    # module_product = "basex-core"
    # module_test = "basex-core"
    # package_product = "org/basex/query/func"
    # package_test = "org/basex/query/func"
    # class_product = "FNDb"
    # class_test = "FNDbTest"
    #
    # source_test = "src/test/java"
    # target_test = "target/test-classes"
    # unit = "target/surefire-reports"
    # cover = "target/site/jacoco"

    # project = "basex"
    # commit = "1a755c51927dacc36ce6120f9500f1cead60c332"
    # number = "1"
    #
    # module_product = "basex-core"
    # module_test = "basex-core"
    # package_product = "org/basex/query/util/pkg"
    # package_test = "org/basex/query/expr"
    # class_product = "RepoManager"
    # class_test = "PackageAPITest"
    #
    # source_test = "src/test/java"
    # target_test = "target/test-classes"
    # unit = "target/surefire-reports"
    # cover = "target/site/jacoco"

    source_ = Source()
    source_.load(project, commit, number)

    diff_ = Diff()
    diff_.load(project, commit, number)

    environment_ = Environment()
    environment_.set(
        project, commit,
        module_product, module_test, package_product, package_test, class_product, class_test,
        source_test, target_test, unit, cover)
    print(environment_)

    output_file_ = open(
        "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
        mode='w',
        encoding="utf-8")
    output_file_.write(
        "----------------------------------------------------------------"
        "----------------------------------------------------------------\n")
    output_file_.close()

    environment_.switch()
    environment_.configure()
    environment_.status()

    state_, description_, message_ = environment_.test_init()
    print(state_)
    print(description_)
    print(message_)

    output_file_ = open(
        "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
        mode='a',
        encoding="utf-8")
    output_file_.write("new state: " + str(state_) + "\n")
    output_file_.write("----------------------------------------------------------------\n")
    output_file_.write("new description: " + description_ + "\n")
    output_file_.write("----------------------------------------------------------------\n")
    output_file_.write("new message\n" + message_ + "\n")
    output_file_.write(
        "----------------------------------------------------------------"
        "----------------------------------------------------------------\n")
    output_file_.close()

    environment_.back_method(source_.new_test, source_.old_test)
    environment_.status()

    state_, description_, message_ = environment_.test()
    print(state_)
    print(description_)
    print(message_)

    output_file_ = open(
        "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
        mode='a',
        encoding="utf-8")
    output_file_.write("old state: " + str(state_) + "\n")
    output_file_.write("----------------------------------------------------------------\n")
    output_file_.write("old description: " + description_ + "\n")
    output_file_.write("----------------------------------------------------------------\n")
    output_file_.write("old message\n" + message_ + "\n")
    output_file_.write(
        "----------------------------------------------------------------"
        "----------------------------------------------------------------\n")
    output_file_.close()

    generated_test_ = Updater.update(source_, diff_, environment_)
    source_file_ = open(
        "/".join([Updater.source_path, "-".join([project, commit, number]) + ".java"]),
        mode='w',
        encoding="utf-8")
    source_file_.write(generated_test_)
    source_file_.close()

    print("Done")
