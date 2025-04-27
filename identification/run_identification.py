import argparse
import csv
import os
import re
import signal
import sys
from pathlib import Path
from typing import List, Optional, Set, Tuple, Dict, Any

from langchain.chains import ConversationChain
from langchain.memory import ConversationBufferWindowMemory
from langchain.prompts import ChatPromptTemplate
from langchain.schema.messages import HumanMessage, SystemMessage
from langchain_core.prompts import (HumanMessagePromptTemplate,
                                    MessagesPlaceholder)
from langchain_openai import ChatOpenAI

# ========== Constants Definition ==========

# --- Model and API Related ---
DEFAULT_LLM_MODEL = "gpt-3.5-turbo-0125"
# DEFAULT_LLM_MODEL = "gpt-4o-2024-05-13" # Alternative model
CONVERSATION_MEMORY_K = 3

# --- File Handling Related ---
DEFAULT_ENCODING = "utf-8"
DEFAULT_CSV_ENCODING = "utf-8-sig" # For Excel compatibility
OLD_PROD_FILENAME = "old_product.java"
NEW_PROD_FILENAME = "new_product.java"
OLD_TEST_FILENAME = "old_test.java"
NEW_TEST_FILENAME = "new_test.java" # Although read, not used in predict function
LABEL_FILENAME = "label.txt"

# --- Output CSV Columns ---
OUTPUT_COLUMNS = ['Project Name', 'Commit ID', 'Sample ID', 'Label', 'Prediction', 'Correct', 'LLM Output']

# --- Prompt Related ---
TAG_OLD_PROD_START = "<old_prod>"
TAG_OLD_PROD_END = "</old_prod>"
TAG_NEW_PROD_START = "<new_prod>"
TAG_NEW_PROD_END = "</new_prod>"
TAG_OLD_TEST_START = "<old_test>"
TAG_OLD_TEST_END = "</old_test>"

# --- Regular Expression for Prediction Extraction ---
# Matches 'yes' or 'no' case-insensitively, surrounded by word boundaries
PREDICTION_REGEX = re.compile(r'\b(yes|no)\b', re.IGNORECASE)

# --- Global list to store results before writing ---
# Used by the signal handler
current_results_batch: List[Tuple[str, str, str, str, Optional[bool], bool, str]] = []
output_file_path_global: Optional[Path] = None

# ========== Utility Functions ==========

def check_openai_api_env_vars() -> None:
    """Checks if OpenAI API Key and Base URL environment variables are set."""
    if not os.getenv("OPENAI_API_KEY"):
        raise ValueError("Environment variable 'OPENAI_API_KEY' is not set. Please set it before running.")
    # Base URL is optional
    # if not os.getenv("OPENAI_API_BASE"):
    #     raise ValueError("Environment variable 'OPENAI_API_BASE' is not set. Please set it before running.")
    # print("OpenAI API Key and Base URL environment variables checked.")

def read_file(file_path: Path) -> str:
    """
    Reads the content of a file specified by the path.

    Args:
        file_path: The path to the file (Path object).

    Returns:
        The content of the file as a string.

    Raises:
        FileNotFoundError: If the file does not exist.
        IOError: If an error occurs during file reading.
    """
    try:
        with open(file_path, 'r', encoding=DEFAULT_ENCODING) as file:
            return file.read()
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
        raise
    except IOError as e:
        print(f"Error: IO error reading file {file_path}: {e}")
        raise

def extract_prediction(text: str) -> Optional[bool]:
    """
    Extracts 'yes' (True) or 'no' (False) from the LLM output text.

    Args:
        text: The output text from the LLM.

    Returns:
        True if 'yes' is found, False if 'no' is found, None otherwise.
    """
    match = PREDICTION_REGEX.search(text)
    if match:
        prediction = match.group(1).lower()
        if prediction == 'yes':
            return True
        elif prediction == 'no':
            return False
    # Fallback: Check common phrases if direct yes/no not found
    text_lower = text.lower()
    if "test code needs updating" in text_lower or "needs to be updated" in text_lower:
        return True
    if "test code does not need updating" in text_lower or "no updates required" in text_lower:
        return False
    print(f"Warning: Could not extract clear 'yes' or 'no' from output: '{text[:100]}...'")
    return None # Indicate ambiguity

def load_experiences_from_csv(file_path: Optional[Path]) -> str:
    """
    Loads summarized experiences from a CSV file (output of the previous script).

    Args:
        file_path: Path to the CSV file containing experiences.

    Returns:
        A formatted string of experiences, or an empty string if file is not provided or invalid.
    """
    if not file_path or not file_path.is_file():
        print(f"Info: Experience file not provided or not found at '{file_path}'. Skipping.")
        return ""

    experiences = []
    try:
        df = pd.read_csv(file_path, encoding=DEFAULT_CSV_ENCODING)
        if 'Final Experience' in df.columns:
            # Extract non-empty experiences
            experiences = [str(exp).strip() for exp in df['Final Experience'].dropna() if str(exp).strip()]
            # Format as a numbered list
            if experiences:
                 # Assuming experiences might already contain numbered lists within <experience> tags.
                 # We just join them. If they are plain text, formatting might be needed.
                 # Let's assume the input format is already suitable for direct inclusion.
                return "\n".join(experiences)
            else:
                print(f"Warning: No valid experiences found in column 'Final Experience' of {file_path}")
                return ""
        else:
            print(f"Warning: Column 'Final Experience' not found in {file_path}")
            return ""
    except Exception as e:
        print(f"Error loading experiences from {file_path}: {e}")
        return ""

# ========== Core Prediction Function ==========

def get_prediction_from_llm(
    old_product_code: str,
    new_product_code: str,
    old_test_code: str,
    positive_experiences_str: str,
    negative_experiences_str: str,
    llm_model_name: str = DEFAULT_LLM_MODEL
) -> Tuple[Optional[bool], str]:
    """
    Uses LangChain and an LLM to predict if test code needs updates.

    Args:
        old_product_code: The old production code snippet.
        new_product_code: The new production code snippet.
        old_test_code: The old test code snippet.
        positive_experiences_str: Formatted string of positive experiences.
        negative_experiences_str: Formatted string of negative experiences.
        llm_model_name: The name of the LLM model to use.

    Returns:
        A tuple containing:
        - The predicted flag (True for 'yes', False for 'no', None for ambiguous).
        - The raw output string from the LLM.
    """
    try:
        llm = ChatOpenAI(temperature=0, model_name=llm_model_name)
        # print(f"LLM model: {llm_model_name}") # Can be verbose
    except Exception as e:
        print(f"Error initializing LLM ({llm_model_name}): {e}")
        return None, f"LLM Initialization Error: {e}"

    # --- Construct System Prompt Dynamically ---
    system_prompt_parts = [
        "You are an expert software testing engineer specializing in analyzing production code changes and updating corresponding test cases.",
        "You will be given the old and new versions of production code along with the test code.",
        "Your task is to determine if the test code needs updates based on the changes in the production code."
    ]
    if positive_experiences_str:
        system_prompt_parts.append("\nHere's what I learned from examples where tests DID need updating (Positive Experiences):")
        system_prompt_parts.append(positive_experiences_str)
    if negative_experiences_str:
         system_prompt_parts.append("\nHere's what I learned from examples where tests did NOT need updating (Negative Experiences):")
         system_prompt_parts.append(negative_experiences_str)
    if positive_experiences_str or negative_experiences_str:
        system_prompt_parts.append("\nUse these principles to help you judge better.")

    system_prompt_parts.append("\nCarefully analyze the provided code snippets.")
    system_prompt_parts.append("Provide your reasoning first, explaining why the test code does or does not need updates based on the production code changes and the learned principles.")
    system_prompt_parts.append("Finally, conclude with a single word on a new line: 'yes' if the test code needs updating, or 'no' if it does not.")
    system_prompt_parts.append("Mandatory: Ensure your final answer is just 'yes' or 'no' on its own line after your reasoning.")

    system_message_content = "\n".join(system_prompt_parts)

    # --- Setup LangChain Conversation ---
    memory = ConversationBufferWindowMemory(return_messages=True, k=CONVERSATION_MEMORY_K)
    human_message_template = ( # Combine the introductory message with the code input structure
        "Below are the old and new versions of the production code and the old test code. "
        "Please analyze them carefully based on the instructions and principles provided.\n\n"
        f"{TAG_OLD_PROD_START}\n{{old_prod}}\n{TAG_OLD_PROD_END}\n\n"
        f"{TAG_NEW_PROD_START}\n{{new_prod}}\n{TAG_NEW_PROD_END}\n\n"
        f"{TAG_OLD_TEST_START}\n{{old_test}}\n{TAG_OLD_TEST_END}\n\n"
        "Remember to provide reasoning and end with 'yes' or 'no'."
    )

    prompts = ChatPromptTemplate.from_messages([
        SystemMessage(content=system_message_content),
        MessagesPlaceholder(variable_name="history"), # History might not be very useful here if each prediction is independent
        HumanMessagePromptTemplate.from_template(human_message_template) # Use template for code input
    ])

    conversation = ConversationChain(llm=llm, prompt=prompts, memory=memory, verbose=False) # verbose=True for debugging

    # --- Format Input and Predict ---
    input_data = {
        "old_prod": old_product_code,
        "new_prod": new_product_code,
        "old_test": old_test_code
    }

    try:
        llm_output = conversation.predict(**input_data)
        # print(f"LLM Output:\n{llm_output}") # For debugging
        predicted_flag = extract_prediction(llm_output)
        return predicted_flag, llm_output
    except Exception as e:
        print(f"Error during LLM prediction: {e}")
        return None, f"Prediction Error: {e}"

# ========== File Processing Functions ==========

def process_sample(
    sample_path: Path,
    project_name: str,
    commit_id: str,
    sample_id: str,
    positive_experiences: str,
    negative_experiences: str,
    llm_model: str
) -> Optional[Tuple[str, str, str, str, Optional[bool], bool, str]]:
    """
    Processes a single sample directory.

    Args:
        sample_path: Path to the sample directory.
        project_name: Name of the project.
        commit_id: Commit ID.
        sample_id: Sample ID.
        positive_experiences: Formatted positive experiences string.
        negative_experiences: Formatted negative experiences string.
        llm_model: Name of the LLM model to use.

    Returns:
        A tuple containing result data, or None if a critical error occurs.
        (project, commit, sample, label, prediction_flag, is_correct, llm_output)
    """
    try:
        old_prod_path = sample_path / OLD_PROD_FILENAME
        new_prod_path = sample_path / NEW_PROD_FILENAME
        old_test_path = sample_path / OLD_TEST_FILENAME
        label_path = sample_path / LABEL_FILENAME
        # new_test_path = sample_path / NEW_TEST_FILENAME # Read if needed later

        # Check if all required files exist
        required_files = [old_prod_path, new_prod_path, old_test_path, label_path]
        if not all(f.exists() for f in required_files):
            print(f"Warning: Skipping sample {sample_path.name} due to missing files.")
            return None

        old_product = read_file(old_prod_path)
        new_product = read_file(new_prod_path)
        old_test = read_file(old_test_path)
        label_str = read_file(label_path).strip()

        if label_str not in ['0', '1']:
            print(f"Warning: Skipping sample {sample_path.name} due to invalid label '{label_str}'.")
            return None

        label_bool = (label_str == '1') # True if label is 1, False if 0

        # Get prediction from LLM
        predicted_flag, llm_output = get_prediction_from_llm(
            old_product, new_product, old_test,
            positive_experiences, negative_experiences, llm_model
        )

        # Determine if prediction is correct
        is_correct = False
        if predicted_flag is not None:
            is_correct = (predicted_flag == label_bool)
            print(f"Processed: {project_name}/{commit_id}/{sample_id} - Label: {label_str}, Prediction: {'Yes' if predicted_flag else 'No'}, Correct: {is_correct}")
        else:
            print(f"Processed: {project_name}/{commit_id}/{sample_id} - Label: {label_str}, Prediction: Ambiguous/Error, Correct: False")


        return (project_name, commit_id, sample_id, label_str, predicted_flag, is_correct, llm_output)

    except FileNotFoundError:
        # Already handled by read_file, but catch again just in case
        print(f"Error: File not found during processing of {sample_path}. Skipping.")
        return None
    except Exception as e:
        print(f"Error processing sample {sample_path}: {e}")
        # Return error information in the output string
        return (project_name, commit_id, sample_id, label_str if 'label_str' in locals() else 'Unknown', None, False, f"Sample Processing Error: {e}")


def process_directory_recursively(
    root_dir: Path,
    processed_samples_ids: Set[Tuple[str, str, str]],
    positive_experiences: str,
    negative_experiences: str,
    llm_model: str
) -> None:
    """
    Recursively processes sample directories under the root directory.
    Appends results to the global `current_results_batch`.

    Args:
        root_dir: The root directory containing project/commit/sample structure.
        processed_samples_ids: A set of (project, commit, sample) tuples already processed.
        positive_experiences: Formatted positive experiences string.
        negative_experiences: Formatted negative experiences string.
        llm_model: Name of the LLM model to use.
    """
    global current_results_batch
    for project_path in root_dir.iterdir():
        if project_path.is_dir():
            project_name = project_path.name
            for commit_path in project_path.iterdir():
                if commit_path.is_dir():
                    commit_id = commit_path.name
                    for sample_path in commit_path.iterdir():
                        if sample_path.is_dir():
                            sample_id = sample_path.name
                            sample_key = (project_name, commit_id, sample_id)

                            if sample_key not in processed_samples_ids:
                                result = process_sample(
                                    sample_path, project_name, commit_id, sample_id,
                                    positive_experiences, negative_experiences, llm_model
                                )
                                if result:
                                    current_results_batch.append(result)
                                    processed_samples_ids.add(sample_key) # Mark as processed immediately
                                    # Optional: Write batch results periodically
                                    # if len(current_results_batch) >= BATCH_WRITE_SIZE:
                                    #     write_results_to_csv(output_file_path_global, current_results_batch, write_header=False)
                                    #     current_results_batch = [] # Clear batch

                            else:
                                # print(f"Skipping already processed sample: {sample_key}") # Can be verbose
                                pass


# ========== CSV Handling Functions ==========

def read_existing_results(output_csv: Path) -> Set[Tuple[str, str, str]]:
    """
    Reads an existing results CSV to identify already processed samples.

    Args:
        output_csv: Path to the output CSV file.

    Returns:
        A set of tuples (project_name, commit_id, sample_id) that have been processed.
    """
    processed_samples = set()
    if not output_csv.is_file():
        return processed_samples # Return empty set if file doesn't exist

    try:
        with open(output_csv, 'r', newline='', encoding=DEFAULT_CSV_ENCODING) as csvfile:
            reader = csv.reader(csvfile)
            header = next(reader, None) # Read header
            if not header or len(header) < 3:
                print(f"Warning: Invalid or empty header in existing results file: {output_csv}")
                return processed_samples

            # Find indices (handle potential column order changes slightly)
            try:
                proj_idx = header.index('Project Name')
                commit_idx = header.index('Commit ID')
                sample_idx = header.index('Sample ID')
            except ValueError as e:
                print(f"Warning: Missing expected columns in {output_csv}: {e}. Cannot resume reliably.")
                return processed_samples # Cannot reliably resume

            for row in reader:
                if len(row) >= max(proj_idx, commit_idx, sample_idx) + 1:
                    project_name = row[proj_idx]
                    commit_id = row[commit_idx]
                    sample_id = row[sample_idx]
                    processed_samples.add((project_name, commit_id, sample_id))
    except Exception as e:
        print(f"Error reading existing results from {output_csv}: {e}")
        # Decide whether to continue with an empty set or stop
        # return set() # Safer option: start fresh if reading fails

    print(f"Found {len(processed_samples)} previously processed samples in {output_csv}")
    return processed_samples


def write_results_to_csv(
    output_csv: Path,
    results_batch: List[Tuple],
    write_header: bool = False
) -> None:
    """
    Appends a batch of results to the output CSV file.

    Args:
        output_csv: Path to the output CSV file.
        results_batch: A list of result tuples to write.
        write_header: Whether to write the header row (usually only if file is new).
    """
    if not results_batch:
        return

    try:
        # Ensure output directory exists
        output_csv.parent.mkdir(parents=True, exist_ok=True)

        mode = 'a' if output_csv.exists() and not write_header else 'w'
        with open(output_csv, mode, newline='', encoding=DEFAULT_CSV_ENCODING) as csvfile:
            writer = csv.writer(csvfile)
            if write_header or mode == 'w':
                writer.writerow(OUTPUT_COLUMNS)
            writer.writerows(results_batch)
        # print(f"Appended {len(results_batch)} results to {output_csv}") # Can be verbose
    except IOError as e:
        print(f"Error writing results to {output_csv}: {e}")
    except Exception as e:
        print(f"Unexpected error writing results: {e}")


# ========== Signal Handler ==========

def signal_handler(sig, frame):
    """Handles interrupt signals (like Ctrl+C) to save progress."""
    global current_results_batch, output_file_path_global
    print("\nInterrupt received! Saving pending results...")
    if output_file_path_global and current_results_batch:
        # Determine if header needs to be written (only if file doesn't exist yet)
        write_header_on_interrupt = not output_file_path_global.exists()
        write_results_to_csv(output_file_path_global, current_results_batch, write_header=write_header_on_interrupt)
        print(f"Saved {len(current_results_batch)} pending results to {output_file_path_global}")
        current_results_batch = [] # Clear batch after saving
    else:
        print("No pending results to save or output file path not set.")
    sys.exit(0)


# ========== Main Execution ==========

def main():
    """Parses arguments and runs the main processing loop."""
    global output_file_path_global # Allow signal handler to access the output path

    parser = argparse.ArgumentParser(
        description="Analyze code changes using LLM and pre-defined experiences to predict test update needs.",
        formatter_class=argparse.ArgumentDefaultsHelpFormatter
    )

    # --- Input/Output Arguments ---
    parser.add_argument(
        "source_dir",
        type=str,
        help="Path to the root directory containing test samples (project/commit/sample structure)."
    )
    parser.add_argument(
        "output_csv",
        type=str,
        help="Path to the output CSV file where results will be saved."
    )
    parser.add_argument(
        "--pos-exp-csv",
        type=str,
        default=None,
        help="Path to the CSV file containing positive experiences (output from clustering script)."
    )
    parser.add_argument(
        "--neg-exp-csv",
        type=str,
        default=None,
        help="Path to the CSV file containing negative experiences (output from clustering script)."
    )

    # --- Model Argument ---
    parser.add_argument(
        "--llm-model",
        type=str,
        default=DEFAULT_LLM_MODEL,
        help="Name of the OpenAI LLM model to use for prediction."
    )

    args = parser.parse_args()

    # --- Setup ---
    check_openai_api_env_vars()
    source_path = Path(args.source_dir)
    output_path = Path(args.output_csv)
    pos_exp_path = Path(args.pos_exp_csv) if args.pos_exp_csv else None
    neg_exp_path = Path(args.neg_exp_csv) if args.neg_exp_csv else None
    output_file_path_global = output_path # Set global path for signal handler

    if not source_path.is_dir():
        print(f"Error: Source directory not found or is not a directory: {source_path}")
        sys.exit(1)

    # --- Load Experiences ---
    print("Loading experiences...")
    positive_experiences = load_experiences_from_csv(pos_exp_path)
    negative_experiences = load_experiences_from_csv(neg_exp_path)
    if not positive_experiences and not negative_experiences:
         print("Warning: No experiences loaded. Predictions will be based solely on the base prompt.")


    # --- Resume Logic ---
    processed_samples = read_existing_results(output_path)
    # Determine if header needs to be written for the first batch
    write_initial_header = not output_path.exists() or output_path.stat().st_size == 0

    # --- Register Signal Handler ---
    signal.signal(signal.SIGINT, signal_handler)
    signal.signal(signal.SIGTERM, signal_handler)

    # --- Run Processing ---
    print(f"Starting processing from: {source_path}")
    print(f"Results will be saved to: {output_path}")
    print(f"Using LLM model: {args.llm_model}")

    # Process directories and collect results in the global batch
    process_directory_recursively(
        source_path,
        processed_samples,
        positive_experiences,
        negative_experiences,
        args.llm_model
    )

    # --- Final Write ---
    print("\nProcessing finished. Writing final batch of results...")
    # Pass write_header=True only if the file was initially empty AND we haven't written anything yet
    final_write_header = write_initial_header and not processed_samples # Check if any samples were processed before
    write_results_to_csv(output_path, current_results_batch, write_header=final_write_header)
    print(f"Total results saved to {output_path}")


if __name__ == "__main__":
    main()
