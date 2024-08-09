import json

from util.react.environment import Environment
from util.tool.diff import Diff
from util.tool.source import Source
from util.updater import Updater

sample_path = "data/set/REACCEPT/test-run"

project = "basex"
commit = "fc1025bf495008bca93cbe046e1a68ed41f2433a"
number = "1"

sample_file = open("/".join([sample_path, project, commit, number, "sample.json"]), "r", encoding="utf-8")
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

print("Done")
