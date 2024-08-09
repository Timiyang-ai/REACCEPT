# REACCEPT

This repository provides the code and data for the paper "REACCEPT: Automated Co-evolution of Production and Test Code Based on Dynamic Validation and Large Language Models", which has been submitted to ICSE 2025. Our work leverages large language models and dynamic validation to fully automate PT co-evolution (i.e., capable of identifying and updating obsolete test code).

Our proposed methodology REACCEPT(REasoning-Action mechanism and Code dynamic validation assisted Co-Evolution of Production and Test code) is divided into two phases, identification and update. 

## Identification

### Contents

- `data`: includes training set for extracting experience and test set for the final identification experiments, both utilizing method-level samples.
- `result`: The results of REACCEPT identification.
- `run_identification.py`: Running REACCEPT identification for the test set.
- `evaluate_code_bleu.py`:  Extracting experience from the training set.
- `requirement.txt`: Providing the PyPI requirement for REACCEPT Identification.

### Environment

The following are the main required Python dependencies and versions  for REACCEPT identification.

```
requests~=2.31.0
lxml~=5.2.1
langchain~=0.1.17
```

You can use `pip install -r requirements.txt`to install these packages. To run LLM you need to install openai packages by running `pip install openai==1.23.6` in shell. 

### Getting Experience

Run `python get_experience.py` to extract the experience for better identification. 

### Identification

1. During the identification phase, you can run with `python run_identification.py` on the test set. 
2. The identification results will be written into a table file. The headers of the table are: Project Name, Commit ID, Sample ID, Label, Update Flag, Predict Label, LLM Output.

## Update

### Contents

- `data`: The dataset, including source code, diff, test project, and pom config used for building knowledge base and evaluating REACCEPT.
- `output`: The logs and results of REACCEPT update and evaluation for different parameters.
- `util`: The source codes of utilities for REACCEPT update, which provides differ, knowledge retriever, test environment, llm, updater and etc.
- `run_updates.py`: Running REACCEPT update for the test set.
- `run_update.py`: Running REACCEPT update for a single sample in the test set.
- `evaluate_code_bleu.py`: Evaluation REACCEPT update by CodeBLEU.
- `requirement.txt`: Providing the PyPI requirement for REACCEPT update.

### Environment

The following are the required Python dependencies and versions  for REACCEPT update.

```
requests~=2.31.0
lxml~=5.2.1
langchain~=0.1.17
codebleu~=0.7.0
```

And you can use `pip install -r requirements.txt`to install these packages. To run LLM you need to install openai packages by running `pip install openai==1.23.6` in shell. And you need to install chromadb for storing and retrievaling the knowledge by use `pip install chromadb==0.5.0`.

To perform dynamic validation, you need to install Git, Maven, and JDK. The projects in our test set require JDK 6, 7, and 8. We recommend using Maven 3.2.5.

To run the CodeBLEU evaluation, you must install [tree-sitter](https://github.com/tree-sitter/tree-sitter) or just tree-sitter-java.

You need to clone the repositories of the test projects from Github. 

```
git clone https://github.com/springside/springside4.git
git clone https://github.com/apache/commons-lang.git
git clone https://github.com/dayatang/dddlib.git
git clone https://github.com/datumbox/datumbox-framework.git
git clone https://github.com/openmrs/openmrs-core.git
git clone https://github.com/BaseXdb/basex.git
```

We provide their pom config (modified for compiling, unit testing, and coverage testing) in `data/environment/pom`. Then configure the path of the repositories and pom files in `util/react/environment.py`. The test projects will be configured automatically before REACCEPT updates the obsolete test codes.

### Building Knowledge Base

Run `python util/react/knowledge.py` to build the knowledge base for retriever. Alternatively, you can just update the obsolete test code as follows for we have provided the knowledge base.

### Update and Dynamic Validation

1. During the update phase, you can run with `python run_updates.py` on the test set. Due to the dynamic validation of test code requiring different JDK versions, You need to run `python run_updates.py` on JDK 6, 7, 8 and modify the JDK versions in `run_updates.py`.
2. You can also run `python run_update.py` with input the project, commit ID, and number for a single sample in the test set.
3. The log and updated test code could be found in the folder `output/<parameter>/log` and `output/<parameter>/source`. There are result and times of dynamic validation in the log file. You can make a dynamic evaluation (for RQ3, RQ4) by these log files. The file `output/output.csv` provides the meaning of states and descriptions in the log.

### Static Evaluation by CodeBLEU

`evaluate_code_bleu.py` is used for calculating CodeBLEU score (for RQ1) of the updated test code.

