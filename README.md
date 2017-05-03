# ENSE 350

My solutions to labs in ENSE 350 - Mathematical Programming for Software Engineers

## Overview

> **Lab 1** - Euclidean Algorithm and Pulverizer  
> **Lab 2** - RSA Cryptosystem  
> **Lab 3** - Root Approximation  
> **Lab 4** - LU Decomposition  
> **Lab 5** - Numerical Integration  

## Compilation

### Eclipse IDE for Java Neon.1

To compile, go to
> File -> New -> Java Project

Name the project, set the `Location` of the project or keep the default, and navigate to the directory of the project in the system explorer.

Move the folder of one of the labs from this repository and merge it with the `src/`
directory where you created the project.

The project files should now be visible in the IDE's `Package Editor`. If not, press `F5` with the `Package Editor` highlighted to refresh the directories.

Press `Ctrl + F11` to build and run, or `F11` to build and debug.

You may receive a window to choose how to run the application. Select `Java Application`.

### IntelliJ IDEA 2016.2

To compile, go to
> File -> New -> Project

Make sure that `Java` is selected in the list on the left side of the window.
Click `Next`, `Next`, name the project, and `Finish`.

Move the folder of one of the labs from this repository and merge it with the `src\`
directory where you created the project.

Then, open the `Project` tab on the left, and expand the root folder.

Expand the file containing `main()`. In the editor window, click the green play button beside it. There, you can choose to either run or debug the program.

### NetBeans IDE 8.2

To compile, go to
> File -> New Project

Click `Next`, name the project, choose a directory for the project, and click
`Finish`.

Right click on the default package that was created with the same name you
have given the project and `Delete` it.

Move the folder of one of the labs from this repository and merge it with the `src/`
directory where you created the project. This will automatically update the
project files in the IDE. Press `F6` to run or `Ctrl + F5` to debug.

## Details
### Lab 1 - Euclidean Algorithm and Pulverizer
Implemented the Euclidean Algorithm and the Pulverizer (Extended Euclidean Algorithm) as well as a method to calculate a fraction in its lowest terms.
### Lab 2 - RSA Cryptosystem
Implemented the RSA cryptosystem algorithm with the ability to encode and decode messages.
### Lab 3 - Root Approximation
Implemented the Newton-Raphson, Bisection, and Secant methods for approximating the roots of a given function.
### Lab 4 - LU Decomposition
Implemented LU decomposition as a method to solve a system of linear equations.
### Lab 5 - Numerical Integration
Implemented a multi-segmented trapezoidal method to numerically integrate a given function over a given interval.
