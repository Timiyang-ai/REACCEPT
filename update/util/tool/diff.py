import difflib
import os

from util.tool.source import Source


class Diff:
    # diff = "data/diff"
    # diff = "data/diff/train"
    diff = "data/diff/test"

    @staticmethod
    def diff_by_source(old, new):
        return "".join(difflib.unified_diff(old.splitlines(True), new.splitlines(True), fromfile="old", tofile="new"))

    @staticmethod
    def diff_by_file(old, new):
        command = "git diff " + old + " " + new
        op = os.popen(command)
        diff = op.buffer.read().decode("utf-8")
        op.close()
        return diff

    @staticmethod
    def diff_all():
        projects = os.listdir(Source.source)
        total_projects = len(projects)
        # print(total_projects)

        count_projects = 0
        for project in projects:
            commits = os.listdir("/".join([Source.source, project]))
            total_commits = len(commits)
            # print(total_commits)

            count_commits = 0
            for commit in commits:
                numbers = os.listdir("/".join([Source.source, project, commit]))
                total_numbers = len(numbers)
                # print(total_numbers)

                count_numbers = 0
                for number in numbers:
                    diff = Diff()
                    diff.diff_file_project_commit(project, commit, number)
                    diff.store()

                    count_numbers += 1
                    print("\t\t\t", count_numbers, "/", total_numbers)
                count_commits += 1
                print("\t\t", count_commits, "/", total_commits)
            count_projects += 1
            print("\t", count_projects, "/", total_projects)

    def __init__(self):
        self.project = None
        self.commit = None
        self.number = None
        self.label = None
        self.diff_product = None
        self.diff_test = None

    def set(self, project, commit, number, label, diff_product, diff_test):
        self.project = project
        self.commit = commit
        self.number = number
        self.label = label
        self.diff_product = diff_product
        self.diff_test = diff_test

    def load(self, project, commit, number):
        self.project = project
        self.commit = commit
        self.number = number

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "label.txt"]),
            "r",
            encoding="utf-8")
        self.label = file.read()
        file.close()

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "diff_product.diff"]),
            "r",
            encoding="utf-8")
        self.diff_product = file.read()
        file.close()

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "diff_test.diff"]),
            "r",
            encoding="utf-8")
        self.diff_test = file.read()
        file.close()

    def store(self):
        if not os.path.exists("/".join([self.diff, self.project, self.commit, self.number])):
            os.makedirs("/".join([self.diff, self.project, self.commit, self.number]))

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "label.txt"]),
            "w",
            encoding="utf-8")
        file.write(str(self.label))
        file.close()

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "diff_product.diff"]),
            "w",
            encoding="utf-8")
        file.write(self.diff_product)
        file.close()

        file = open(
            "/".join([self.diff, self.project, self.commit, self.number, "diff_test.diff"]),
            "w",
            encoding="utf-8")
        file.write(self.diff_test)
        file.close()

    def diff_source_source(self, source):
        self.project = source.project
        self.commit = source.commit
        self.number = source.number
        self.label = source.label
        self.diff_product = self.diff_by_source(source.old_product, source.new_product)
        self.diff_test = self.diff_by_source(source.old_test, source.new_test)

    def diff_source_project_commit(self, project, commit, number):
        source = Source()
        source.load(project, commit, number)
        self.diff_source_source(source)

    def diff_file_source(self, source):
        self.project = source.project
        self.commit = source.commit
        self.number = source.number
        self.label = source.label
        os.chdir("/".join([source.source, source.project, source.commit, source.number]))
        self.diff_product = self.diff_by_file("old_product.java", "new_product.java")
        self.diff_test = self.diff_by_file("old_test.java", "new_test.java")
        os.chdir(r"E:\Research\Test\TestCase\Co-Evolution\co-evolution")

    def diff_file_project_commit(self, project, commit, number):
        source = Source()
        source.load(project, commit, number)
        self.diff_file_source(source)


if __name__ == "__main__":
    # diff
    Diff.diff_all()
