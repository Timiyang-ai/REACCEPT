import csv
import os
import re

from lxml import html

from util.tool.source import Source


class Environment:
    root = os.getcwd()

    environment = "data/environment/project"

    environment_ = "data\\environment\\project"

    pom = "data\\environment\\pom"

    def __init__(self):
        self.project = None
        self.commit = None

        self.module_product = None
        self.module_test = None
        self.package_product = None
        self.package_test = None
        self.class_product = None
        self.class_test = None

        self.source_test = None
        self.target_test = None
        self.unit = None
        self.cover = None

        self.package_product_dot = None
        self.package_test_dot = None

        self.source_class_test = None
        self.target_class_test = None
        self.unit_package_class_test = None
        self.cover_class_product = None
        self.cover_jacoco = None

        self.coverage = None

    def set(self, project, commit,
            module_product, module_test, package_product, package_test, class_product, class_test,
            source_test, target_test, unit, cover):
        self.project = project
        self.commit = commit

        self.module_product = module_product
        self.module_test = module_test
        self.package_product = package_product
        self.package_test = package_test
        self.class_product = class_product
        self.class_test = class_test

        self.source_test = source_test
        self.target_test = target_test
        self.unit = unit
        self.cover = cover

        self.package_product_dot = ".".join(self.package_product.split("/"))
        self.package_test_dot = ".".join(self.package_test.split("/"))

        self.source_class_test = "/".join([
            self.module_test, self.source_test, self.package_test, self.class_test + ".java"])
        self.target_class_test = "/".join([
            self.module_test, self.target_test, self.package_test, self.class_test + ".class"])
        self.unit_package_class_test = "/".join([
            self.module_test, self.unit, ".".join([self.package_test_dot, self.class_test, "txt"])])
        self.cover_class_product = "/".join([
            self.module_product, self.cover, self.package_product_dot, self.class_product + ".html"])
        self.cover_jacoco = "/".join([
            self.module_product, self.cover, "jacoco.csv"])

        self.coverage = 0.0

    def status(self):
        os.chdir("/".join([self.environment, self.project]))

        command = "git status"
        op = os.popen(command)
        result = op.read()
        # result = op.buffer.read().decode(encoding='utf-8')
        # result = op.buffer.read().decode(encoding='gbk')
        op.close()
        print(result)

        os.chdir(self.root)

    def switch(self):
        os.chdir("/".join([self.environment, self.project]))

        command = "git reset --hard"
        op = os.popen(command)
        # result = op.read()
        result = op.buffer.read().decode(encoding='utf-8')
        # result = op.buffer.read().decode(encoding='gbk')
        op.close()
        print(result)

        command = "git checkout " + self.commit
        op = os.popen(command)
        result = op.read()
        # result = op.buffer.read().decode(encoding='utf-8')
        # result = op.buffer.read().decode(encoding='gbk')
        op.close()
        print(result)

        os.chdir(self.root)

    def configure(self):
        command = " ".join([
            "copy",
            "\\".join([self.pom, self.project, self.commit, "pom.xml"]),
            "\\".join([self.environment_, self.project]),
            "/y"])
        op = os.popen(command)
        result = op.read()
        op.close()
        print(result)

    def back_method(self, new_test, old_test):
        test_file = open("/".join([self.environment, self.project, self.source_class_test]), mode="r", encoding="utf-8")
        code_test = test_file.read()
        test_file.close()

        code_test = code_test.replace(new_test, old_test)

        test_file = open("/".join([self.environment, self.project, self.source_class_test]), mode="w", encoding="utf-8")
        char_num = test_file.write(code_test)
        test_file.close()

        print("Done old_test, write", char_num, "characters without CRLF")

    def update_method(self, last_test, generated_test):
        test_file = open("/".join([self.environment, self.project, self.source_class_test]), mode="r", encoding="utf-8")
        code_test = test_file.read()
        test_file.close()

        code_test = code_test.replace(last_test, generated_test)

        test_file = open("/".join([self.environment, self.project, self.source_class_test]), mode="w", encoding="utf-8")
        char_num = test_file.write(code_test)
        test_file.close()

        print("Done update, write", char_num, "characters without CRLF")

    def test_init(self):
        os.chdir("/".join([self.environment, self.project]))

        # maven clean
        print("maven clean\n")
        op = os.popen("mvn clean")
        op.close()

        # maven test
        print("maven test\n")
        # op = os.popen("mvn test")
        op = os.popen("mvn test -DfailIfNoTests=false -Dtest=" + "/".join([self.package_test, self.class_test]))

        # read test messages
        # result = op.read()
        # result = op.buffer.read().decode(encoding='utf-8')
        result = op.buffer.read().decode(encoding='gbk')
        op.close()
        # print(result)

        # compile error
        if not os.path.exists(self.target_class_test):
            state = 0
            description = "compile error"
            # collect the compile error messages, parse the compile error messages from the java compiler output
            error_list = re.findall('\[ERROR] (.*\n(?: +.*\n)*)', result)
            message = ""
            for e in error_list:
                message += e
        # compile success
        else:
            # read the unit test result, collect the unit test messages
            report_file = open(self.unit_package_class_test, mode='r', encoding="utf-8")
            report = report_file.read()
            report_file.close()

            # unit test failure
            if "FAILURE" in report:
                state = 1
                description = "unit test failure"
                message = report
            # unit test success
            else:
                # read the coverage test result, collect the coverage messages
                coverage_file = open(self.cover_jacoco, mode="r", encoding="utf-8")
                coverage_csv = csv.reader(coverage_file)
                coverage = 0.0
                for row in coverage_csv:
                    if row[1] == self.package_product_dot and row[2] == self.class_product:
                        # instruction_missed = int(row[3])
                        # instruction_covered = int(row[4])
                        # instruction_coverage = instruction_covered / (instruction_missed + instruction_covered)
                        # coverage = instruction_coverage
                        line_missed = int(row[7])
                        line_covered = int(row[8])
                        line_coverage = line_covered / (line_missed + line_covered)
                        coverage = line_coverage
                        # method_missed = int(row[11])
                        # method_covered = int(row[12])
                        # method_coverage = method_covered / (method_missed + method_covered)
                        # coverage = method_coverage
                        self.coverage = coverage
                        break
                coverage_file.close()

                # uncover
                if coverage < self.coverage:
                    state = 2
                    description = "uncover, coverage：" + str(coverage)
                    # collect the coverage messages
                    tree = html.parse(self.cover_class_product)
                    xpath1 = '//td[@id="a0"]/a/text()'
                    element = tree.xpath(xpath1)
                    # xpath2 = '/html/body/table/tbody/tr[1]/td[1]/a/text()'
                    # element = tree.xpath(xpath2)
                    message = "uncover：" + str(element[0])
                # cover
                else:
                    state = 3
                    description = "pass, coverage：" + str(coverage)
                    message = ""
        os.chdir(self.root)

        return state, description, message

    def test(self):
        os.chdir("/".join([self.environment, self.project]))

        # maven clean
        print("maven clean\n")
        op = os.popen("mvn clean")
        op.close()

        # maven test
        print("maven test\n")
        # op = os.popen("mvn test")
        op = os.popen("mvn test -DfailIfNoTests=false -Dtest=" + "/".join([self.package_test, self.class_test]))

        # read test messages
        # result = op.read()
        # result = op.buffer.read().decode(encoding='utf-8')
        result = op.buffer.read().decode(encoding='gbk')
        op.close()
        # print(result)

        # compile error
        if not os.path.exists(self.target_class_test):
            state = 0
            description = "compile error"
            # collect the compile error messages, parse the compile error messages from the java compiler output
            error_list = re.findall('\[ERROR] (.*\n(?: +.*\n)*)', result)
            message = ""
            for e in error_list:
                message += e
        # compile success
        else:
            # read the unit test result, collect the unit test messages
            report_file = open(self.unit_package_class_test, mode='r', encoding="utf-8")
            report = report_file.read()
            report_file.close()

            # unit test failure
            if "FAILURE" in report:
                state = 1
                description = "unit test failure"
                message = report
            # unit test success
            else:
                # read the coverage test result, collect the coverage messages
                coverage_file = open(self.cover_jacoco, mode="r", encoding="utf-8")
                coverage_csv = csv.reader(coverage_file)
                coverage = 0.0
                for row in coverage_csv:
                    if row[1] == self.package_product_dot and row[2] == self.class_product:
                        # instruction_missed = int(row[3])
                        # instruction_covered = int(row[4])
                        # instruction_coverage = instruction_covered / (instruction_missed + instruction_covered)
                        # coverage = instruction_coverage
                        line_missed = int(row[7])
                        line_covered = int(row[8])
                        line_coverage = line_covered / (line_missed + line_covered)
                        coverage = line_coverage
                        # method_missed = int(row[11])
                        # method_covered = int(row[12])
                        # method_coverage = method_covered / (method_missed + method_covered)
                        # coverage = method_coverage
                        break
                coverage_file.close()

                # uncover
                if coverage < self.coverage:
                    state = 2
                    description = "uncover, coverage：" + str(coverage)
                    # collect the coverage messages
                    tree = html.parse(self.cover_class_product)
                    xpath1 = '//td[@id="a0"]/a/text()'
                    element = tree.xpath(xpath1)
                    # xpath2 = '/html/body/table/tbody/tr[1]/td[1]/a/text()'
                    # element = tree.xpath(xpath2)
                    message = "uncover：" + str(element[0])
                # cover
                else:
                    state = 3
                    description = "pass, coverage：" + str(coverage)
                    message = ""
        os.chdir(self.root)

        return state, description, message


if __name__ == "__main__":
    # test
    project_ = "basex"
    commit_ = "0b2ef31462791b3f8266504ae39274f0b3ed6149"
    number = "1"

    module_product_ = "basex-core"
    module_test_ = "basex-core"
    package_product_ = "org/basex/query/func"
    package_test_ = "org/basex/query/func"
    class_product_ = "FNDb"
    class_test_ = "FNDbTest"

    source_test_ = "src/test/java"
    target_test_ = "target/test-classes"
    unit_ = "target/surefire-reports"
    cover_ = "target/site/jacoco"

    # project_ = "basex"
    # commit_ = "0b2ef31462791b3f8266504ae39274f0b3ed6149"
    # number = "2"
    #
    # module_product_ = "basex-core"
    # module_test_ = "basex-core"
    # package_product_ = "org/basex/query/func"
    # package_test_ = "org/basex/query/func"
    # class_product_ = "FNDb"
    # class_test_ = "FNDbTest"
    #
    # source_test_ = "src/test/java"
    # target_test_ = "target/test-classes"
    # unit_ = "target/surefire-reports"
    # cover_ = "target/site/jacoco"

    # project_ = "basex"
    # commit_ = "1a755c51927dacc36ce6120f9500f1cead60c332"
    # number = "1"
    #
    # module_product_ = "basex-core"
    # module_test_ = "basex-core"
    # package_product_ = "org/basex/query/util/pkg"
    # package_test_ = "org/basex/query/expr"
    # class_product_ = "RepoManager"
    # class_test_ = "PackageAPITest"
    #
    # source_test_ = "src/test/java"
    # target_test_ = "target/test-classes"
    # unit_ = "target/surefire-reports"
    # cover_ = "target/site/jacoco"

    source = Source()
    source.load(project_, commit_, number)

    environment = Environment()
    environment.set(
        project_, commit_,
        module_product_, module_test_, package_product_, package_test_, class_product_, class_test_,
        source_test_, target_test_, unit_, cover_)
    print(environment)

    environment.switch()
    environment.configure()
    environment.status()

    state_, description_, message_ = environment.test_init()
    print(state_)
    print(description_)
    print(message_)

    environment.back_method(source.new_test, source.old_test)
    environment.status()

    state_, description_, message_ = environment.test()
    print(state_)
    print(description_)
    print(message_)

    print("Done")
