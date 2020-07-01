# autotest-plugin

![Build](https://github.com/JavaUnchained/AutoTestIntelijPugin/workflows/Build/badge.svg)

<!-- Plugin description -->


<!-- Plugin description end -->

## Installation
  0. Need to have JDK 1.8 only.
  1. Change Intellij IDEA version to 2019 (or lower, 2019.3.4 is recommended);
  2. Download the [latest release](https://github.com/JavaUnchained/AutoTestIntelijPugin/releases/latest) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>;
  3. Change Intellij IDEA runtime. You need install Runtime plugin and download JDK - Runtime version 1.8.0_252 -1649 relese amd64;
  4. Build *cmd-jbse* project or donwload compiled [JBSE JAR file](./all-in-one-jar-1.0-SNAPSHOT.jar);
  5. Dowloads modified JBSE project from [this repo](https://github.com/Team-Black/JavaAutoTest) or download and unzipping [this archive](./JBSEhome.rar);
  6. Download and compile [Z3 pathresolver](https://github.com/Z3Prover/z3);
  7. Open tesable project and go to <kbd>⚙️ Settings</kbd> -> <kbd>Other</kbd> -> <kbd>AutoTest Settings</kbd>  and specify all the required paths (path to JBSE project, path to JBSE jar, path to Z3 solver and path to JRE (1.8 only);
  8. Click on the right mouse button on the desired class and click on "Create test"
  9. Wait until you see a message that the generation was successful. Notifications in progress are visible at the bottom left.

## Requirements and restrictions
For the project under test, you must have a compiled test class. If you testing Example.java then you need to have Example.class. 
The compiled classes must also follow one of the following paths: "/out" for simple java projects, "target/classes" for maven projects and "build/classes" for Gradle project ( these are usually the default outputs).
This is necessary for searching for descriptors and for javassist to work.
