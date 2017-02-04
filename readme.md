# CLAN

CLAN stands for Closely reLated ApplicatioNs. 

The original paper, by Collin McMillan, can be found here:
http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.380.8742&rep=rep1&type=pdf

This approach is implemented on GitHub, to be compared with RepoPal. RepoPal is a system to detect similarity of GitHub repositories based on stars and texual similarity. Sourcecode of RepoPal can be found here: https://github.com/Qualia-Li/RepoPal.

For more information related to the research on Detecting Similar Repositories on GitHub, and the methdology to compare CLAN and RepoPal, please visit http://www.liquanlai.com/blog/?p=195.

##Feature

CLAN computes similarity between Java applications by comparing the API calls made by the two applications.

## Specification

Change values in `Const.java` to configure:

- `ROOT_DIR`: a `.txt` file which contains the list of directories that are going to be checked. Data should be in form of `id,directory`, in which `number` stands for a unique id of a specific repository and `directory` stands for the directory where the repository locates.

- `JDK_DIR`: a `.txt` file which contains the list of jdk methods that are counted in the evaluation vector. Data should be in form of `name`, in which `name` is the name of a JDK method.

- `VEC_DIR`: a `.txt` file of all evaluation vectors calculated. Repository IDs are also included.

- `AIM_DIR`: ID of the aim repository.

- `ANS_AMT`: the wanted amount of answer to be shown.
