# Code creation with AI - our approach.

The wcm core components library is pretty extensive, but many details (especially editing and many finer points
about the site structure and usage of AEM) are not relevant. Porting the Java classes to Composum would be a
difficult task. But since it has excellent documentation (e.g. README.md for each component) we can try to generate
new Java classes, and can try to migrate the dialogs to Composum conventions automatically.

## General approach

### Caching and versioning

We support the generation of files using an AI, specifically ChatGPT. A generation task can have several input files.
Some of them can be prompt files with task descriptions, and some of them source files to be processed. The output
of each task is one text file. A complex task can have several steps leading to several intermediate files.

Since ChatGPT is not particularily fast not free and the generation results have to be manually checked, this is
heavily cached.
Into each output file we write the versions of all the input files from which it was generated into a comment.
When the tasks are run, we compare the
versions of all the input files with the versions recorded in the comment, and only regenerate the output file if
the versions have changed. An input file can have a version comment that explicitly states the version, or we take the
an abbreviated SHA256 hash of the input file as version. It is possible to explicitly state the versions in
version comments in the input files to avoid regenerating all files if minor details e.g. in a prompt file are
changed - only when the prompt file version comment is changed everything is regenerated.

A version comment can e.g. look like this:

        // AIGenVersion(ourversion, inputfile1@version1, inputfile2@version2, ...)

where ourversion and version1 and version2 are hashes. ourversion is the hash of the original output of the AI.
The comment syntax (in this case //) is ignored - we just look for the AIGenVersion via regex.

Normally the intermediate and final results should be checked in with Git. That ensures manual checks when
they are regenerated, and minimizes regeneration.

### Task chaining

The AI processing is done by generating a task list programmatically (with some DSL), and then traversing the tasks.
Each task is reexecuted when the input versions do not match the recorded input versions or the target file is not
there. For simplicity, we rely on the user to create and execute the tasks in an order that represents their
depencence.

### An example

TODO: make specific example for model generation.

### Limitations and additional ideas

- It'd be nice to be able to continue the conversation - that provides an implicit explanation for the added artifacts.
- This means the resulting file is always completely regenerated. How to make differential changes? One idea would 
  be to give the previous file to the AI and ask it to make minimal changes.
- In general: framework / strategy to fix things.
  - support to ask explanations for things that go wrong / get suggestions for prompt changes

#### Possible restructuring

- The AITask might just be a wrapper around the ChatCompletionBuilder. This also makes it easy to ask for an
  explanation afterwards, or simply continue the conversation.
- Admit other AIs than OpenAI. (Local models?)
