# CLAN

Closely reLated ApplicatioNs

The original paper can be found here:
http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.380.8742&rep=rep1&type=pdf

This method is implemented on GitHub to compare the result with RepoPal. RepoPal can be found here: https://github.com/Qualia-Li/RepoPal.

For more information about the research about Detecting Similar Repositories on GitHub, please visit http://www.liquanlai.com/blog/?p=195.

##Feature

CLAN computes similarity between Java applications by comparing the API calls made by the two applications.

## Specification

Change values in `Const.java` to configure:

- `ROOT_DIR`: a `.txt` file which contains the list of directories that are going to be checked. Data should be in form of `id,directory`, in which `number` stands for a unique id of a specific repository and `directory` stands for the directory where the repository locates.

- `JDK_DIR`: a `.txt` file which contains the list of jdk methods that are counted in the evaluation vector. Data should be in form of `name`, in which `name` is the name of a JDK method.

- `VEC_DIR`: a `.txt` file of all evaluation vectors calculated. Repository IDs are also included.

- `AIM_DIR`: ID of the aim repository.

- `ANS_AMT`: the wanted amount of answer to be shown.
