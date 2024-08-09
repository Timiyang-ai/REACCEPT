import json
import os

from util.react.environment import Environment
from util.tool.diff import Diff
from util.tool.source import Source
from util.updater import Updater

sample_path = "data/set/REACCEPT/test-run"

jdk = "6"
# jdk = "7"
# jdk = "8"

break_point_project = 0
break_point_commit = 0
break_point_number = 0

projects = os.listdir(sample_path)
total_project = len(projects)
for count_project in range(break_point_project, total_project):
    project = projects[count_project]
    commits = os.listdir("/".join([sample_path, project]))
    total_commit = len(commits)
    for count_commit in range(break_point_commit, total_commit):
        commit = commits[count_commit]
        numbers = os.listdir("/".join([sample_path, project, commit]))
        total_number = len(numbers)
        for count_number in range(break_point_number, total_number):
            number = numbers[count_number]
            sample_file = open("/".join([sample_path, project, commit, number, "sample.json"]))
            sample = json.load(sample_file)
            sample_file.close()

            project = sample["project"]
            commit = sample["commit"]
            number = sample["number"]

            module_product = sample["module_product"]
            module_test = sample["module_test"]
            package_product = sample["package_product"]
            package_test = sample["package_test"]
            class_product = sample["class_product"]
            class_test = sample["class_test"]

            source_test = sample["source_test"]
            target_test = sample["target_test"]
            unit = sample["unit"]
            cover = sample["cover"]

            if jdk == sample["jdk"]:
                source = Source()
                source.load(project, commit, number)

                diff = Diff()
                diff.load(project, commit, number)

                environment = Environment()
                environment.set(
                    project, commit,
                    module_product, module_test, package_product, package_test, class_product, class_test,
                    source_test, target_test, unit, cover)
                print(environment)

                output_file = open(
                    "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
                    mode='w',
                    encoding="utf-8")
                output_file.write(
                    "----------------------------------------------------------------"
                    "----------------------------------------------------------------\n")
                output_file.close()

                environment.switch()
                environment.configure()
                environment.status()

                state, description, message = environment.test_init()
                print(state)
                print(description)
                print(message)

                output_file = open(
                    "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
                    mode='a',
                    encoding="utf-8")
                output_file.write("new state: " + str(state) + "\n")
                output_file.write("----------------------------------------------------------------\n")
                output_file.write("new description: " + description + "\n")
                output_file.write("----------------------------------------------------------------\n")
                output_file.write("new message\n" + message + "\n")
                output_file.write(
                    "----------------------------------------------------------------"
                    "----------------------------------------------------------------\n")
                output_file.close()

                environment.back_method(source.new_test, source.old_test)
                environment.status()

                state, description, message = environment.test()
                print(state)
                print(description)
                print(message)

                output_file = open(
                    "/".join([Updater.output_path, "-".join([project, commit, number]) + ".txt"]),
                    mode='a',
                    encoding="utf-8")
                output_file.write("old state: " + str(state) + "\n")
                output_file.write("----------------------------------------------------------------\n")
                output_file.write("old description: " + description + "\n")
                output_file.write("----------------------------------------------------------------\n")
                output_file.write("old message\n" + message + "\n")
                output_file.write(
                    "----------------------------------------------------------------"
                    "----------------------------------------------------------------\n")
                output_file.close()

                generated_test = Updater.update(source, diff, environment)
                source_file = open(
                    "/".join([Updater.source_path, "-".join([project, commit, number]) + ".java"]),
                    mode='w',
                    encoding="utf-8")
                source_file.write(generated_test)
                source_file.close()
            count_number += 1
            print("\t\t\t", count_number, "/", total_number)
        count_commit += 1
        print("\t\t", count_commit, "/", total_commit)
    count_project += 1
    print("\t", count_project, "/", total_project)
print("Done")
