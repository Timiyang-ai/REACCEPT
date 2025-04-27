# REACCEPT

This repository provides the code and data for the paper "REACCEPT: Automated Co-evolution of Production and Test Code Based on Dynamic Validation and Large Language Models". Our work leverages large language models and dynamic validation to fully automate PT co-evolution (i.e., capable of identifying and updating obsolete test code).

Our proposed methodology REACCEPT (REasoning-Action mechanism and Code dynamic validation assisted Co-Evolution of Production and Test code) is divided into two phases, identification and update. 



## Identification

### Workflow

1\.  **Experience Extraction**: Extract initial "experience" descriptions from labeled code samples. 

2\.  **Experience Clustering & Summarization**: Cluster and summarize the initial experience to distill core principles.  

3\. **Experience-Based Identification**: Use the summarized principles to guide the LLM in predicting update needs for new samples.

### Prerequisites

- **Python**: >= 3.8
- **Dependencies**: `openai`, `langchain`, `langchain-openai`, `pandas`, `numpy`, `scikit-learn`, `sentence-transformers`, `tiktoken`, `tqdm`

  ```bash
  pip install openai langchain langchain-openai pandas numpy scikit-learn sentence-transformers tiktoken tqdm
  ```

- **Environment Variables**: Set `OPENAI_API_KEY` (required) and `OPENAI_API_BASE` (optional).

  ```bash
  # Linux/macOS
  export OPENAI_API_KEY="YOUR_API_KEY"
  # Windows (CMD)
  set OPENAI_API_KEY=YOUR_API_KEY
  # Windows (PowerShell)
  $env:OPENAI_API_KEY="YOUR_API_KEY"
  ```

### Data Structure

Input data should follow this structure:

```
<data_root_directory>/
├── train/ # Training data (for extraction/summarization)
│   └── <project_name>/<commit_id>/<sample_id>/
│       ├── old_product.java
│       ├── new_product.java
│       ├── old_test.java
│       └── label.txt # Contains '0' or '1'
└── test/ # Test data (for prediction)
    └── <project_name>/<commit_id>/<sample_id>/
        ├── old_product.java
        ├── new_product.java
        └── old_test.java
```

### Running the Pipeline

Assume the refactored scripts are saved as `extract_raw_experiences.py`, `cluster_summarize_experiences.py`, and `run_identification.py`.

#### Step 1: Extract Initial Experiences

```bash
python extract_raw_experiences.py <path_to_train_data> <output_csv_path> [--max-samples N] [--model MODEL_NAME]
# Example:
python extract_experiences.py ./data/train ./results/step1_initial_experiences.csv
```

**Output**: A CSV file containing initial experiences (column: Experience).

#### Step 2: Cluster and Summarize Experiences

```bash
python cluster_summarize_experiences.py <step1_output_csv> <output_directory> [--embed-model EMBED_MODEL] [--max-clusters-pos N] [--max-clusters-neg N]
# Example:
python cluster_summarize_experiences.py ./results/step1_initial_experiences.csv ./results/step2_summarized
```

**Output**: Generates `final_positive_experiences.csv` and `final_negative_experiences.csv` in the specified output directory.

#### Step 3: Experience-Based Identification

```bash
python run_identification.py <path_to_test_data> <output_csv_path> [--pos-exp-csv <positive_exp_csv>] [--neg-exp-csv <negative_exp_csv>] [--llm-model MODEL_NAME]
# Example:
python identify_update_needs.py ./data/test ./results/step3_predictions.csv --pos-exp-csv ./results/step2_summarized/final_positive_experiences.csv --neg-exp-csv ./results/step2_summarized/final_negative_experiences.csv
```

**Output**: A CSV file containing prediction results (columns: Prediction, Correct).

### Notes

- **Model Selection**: Use `--model`, `--llm-model`, `--embed-model` arguments to specify different models.
- **Parameter Tuning**: Steps 2 and 3 offer various arguments to adjust clustering, summarization, and prediction behavior.
- **Resuming Execution**: Scripts 1 and 3 support resuming from interruption. They will read the existing output CSV and skip already processed samples.
- **Prompt Engineering**: Modify the `SYSTEM_MESSAGE` constants within the scripts to adjust LLM behavior.



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

The following are the required Python dependencies and versions for REACCEPT update.

```
requests~=2.31.0
lxml~=5.2.1
langchain~=0.1.17
codebleu~=0.7.0
```

And you can use `pip install -r requirements.txt` to install these packages. To run LLM you need to install openai packages by running `pip install openai==1.23.6` in shell. And you need to install chromadb for storing and retrievaling the knowledge by use `pip install chromadb==0.5.0`.

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

