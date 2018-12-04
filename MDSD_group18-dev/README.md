# MDSD_group18

## Setup
Based on that you son't get maven to function properly and download all that you need in IntelliJ. If using IntelliJ, open the pom.xml file and then hopefully IntelliJ will recognize the Maven structure and download all dependencies for you.

First install git and java on your computer. 
To install Maven use [this guide](https://www.mkyong.com/maven/how-to-install-maven-in-windows/). 
When downloading the repo do it by using:
```
git clone repoURL 
```
Go to the same directory as the pom.xml file is in and then use the maven command:
```
mvn install
```
This will download all components that is needed for the simulator and the program to work.
Then go to the development branch with:
```
git checkout dev
```
Then create your new branch for yourself/the feature that you will implement and push it.
```
git checkout -b newBranch
git push -u origin newBranch
```
When done with the desired feature merge with the dev branch. How to do this is described in the Git cheat sheet in the "git" Slack channel.
