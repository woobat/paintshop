Paint Shop Problem Solver
-------------------------

![color_palette](docs/palette.jpg)


[![Build Status](https://travis-ci.org/woobat/paintshop.svg?branch=master)](https://travis-ci.org/woobat/paintshop) [![Coverage Status](https://coveralls.io/repos/github/woobat/paintshop/badge.svg)](https://coveralls.io/github/woobat/paintshop)
        
Contents
--------

[Problem](#problem)

[Solution](#solution)

[Good Parts](#good-parts)

[To do](#to-do)

[Prerequisite](#prerequisite)

[Quick Start](#quick-start)

[Run](#run)
 
* * * * *

Problem
=======

You have a paint shop, and being the owner of the shop you have to optimize the operational expense to keep the shop profitable. However being a prudent shopkeeper you *put customers first and walk back to a solution* such that the solution can keep the cost to a minimum.

This leads you to solve a *Constraint Satisfaction Problem (CSP)*. Here you have a set of few different colors of paint that you can sell and each color can be either *gloss* or *matte*. You want to mix the colors, so that:

- There is just one batch for each color, and it's either gloss or matte.

- For each customer, there is at least one color they like.

- You make as few mattes as possible (because they are more expensive).

Solution
========

This is essentially a search problem where there is a need to systematically search the complete problem space. 

- An initial solution can be just to approach this problem as a recursive backtracking solution where the complete search space will be enumerated and the solution will be picked which has the minimum cost to satisfy the given constraint. So for this specific problem there is a need to traverse each color and domain and to pick the solution that will have minimum number of *mattes* to satisfy all the customers.

- Essentially this naive solution will explore all the leaf nodes of the search space and the leaf node having least cost will be the optimal solution. However, the complexity lies on the fact that this given search space can grow quite fast for a large number of colors. As an example, if there are *M* colors having *N* possible domain, it is essentially ![leaf states](http://latex.codecogs.com/gif.latex?M%5E%7B%7CD%7C%7D) number of leaf nodes in the search space that needs to be explored before determining the optimal solution.

- In order to reduce the search space couple of interesting optimization techniques can be employed, namely forward checking, choosing the most constrained variable a.k.a minimum remaining values (MRV) or *fail first* heuristic.

Good Parts
===========

- **Modularization**: Overall structure of this application was designed by breaking the large system in to smaller problem constructs such that the resulting system is easier to reason about. 

- **Testing**: Testing was not an after thought and code was well tested from the very beginning and the end result is a comprehensive test suite covering all functionality. However there are rooms for improvement here and a TDD driven approach could have been undertaken.

- **Methodical Approach**: This application was built in a systematic way - from the very beginning the ground work was laid out which made the overall development process methodical and pain free. Specially for real world projects having many committer it is essential to establish the ground rules early i.e. generating a consensus around code style, continuous integration, testing style which is essential for completing the project in time and reducing conflict during the development and maintaining the product in operation in the longer term. For this project, CI and code coverage was introduced in the very beginning.   

- **CI integration**: Continuous Integration (CI) was introduced early on the project. For this project [Travis CI](https://travis-ci.org/) was used as it is a free CI service for open sourced project and needs minimum configuration.

- **Code Coverage**: From an early stage code coverage was integrated.

  - Being a Java project [Java Code Coverage Library (JaCoCo)](http://jacoco.org/jacoco/) was a reasonable choice. 

  - Interesting part was to incorporate with GitHub in a seamless fashion and in fact there are a couple of established solutions are available. 
  
  - [Coveralls](https://coveralls.io/) was chosen in this project which integrates with *Travis CI* easily and code coverage report is executed as part of the build process. 

  - Now regarding the contentious issue of how much code coverage is good enough - there are many good arguments on this topic. My own philosophy is having a well written test suite already attains a reasonable coverage and having the code coverage tools in place gives insight what part of the code is not tested. At the same time I generally look into the code coverage report as part of the code review - a quick glance sometime helps me. Now regarding working on a team project how to address the issue of code coverage - I believe this discussion belongs during the ground rule setup for a project and the best is to reach into a consensus early on and review the policy on an ongoing basis if there is any such need.

- **Error Handling**: Edge cases during parsing the csv file is handled. However, a few edge cases is left as a to-do.

To do
=======

- **CheckStyle**: Adhering to a consistent programming style improves code quality, readability and in some case reduces friction during the PR review. Add [Check Style](https://github.com/checkstyle/checkstyle) in the CI pipeline.

- **Automated test suite generator**: For testing this solution against a large problem space, it will be handy to create an automated test suite generator. This tool will become very handy to assist during further functional testing and performance testing.

- **More Functional Testing**: Functionality has been tested mostly using small problem space and therefore there is a need to test against a more varied variable assignments and large problem space. 

- **Performance testing**: Code was not tested against large input and one interesting work can be to test and benchmark with large input set.

- **Using Standard Library**: There are well recognized libraries for solving Constraint Satisfaction Problems and any such library ([choco solver](http://www.choco-solver.org/)) can be utilized for this problem. It fact, using such libraries will become useful to maintain this project in the production environment as these libraries provide higher level of abstraction to specify the CSP problems. At the same time if the team needs to solve a similar problem, using the standard solver will help to develop solution faster in a maintainable way.

- **Control Flow**: A *Checked Exception* (`ConstraintViolation`) is used in the `AbstractSolver` class in the flow control when there is no valid assignment. At the time of reviewing the code this is coming up to me this might have been approached better. I am happy to revisit this scenario this if there is any question that comes up during PR review.

- **Code Comment**: Code comments can be enhanced specially for the constraint solver methods.

Prerequisite
============

- JDK 8

- maven 3.5

Quick Start
============

- Clone this repository

```
git clone https://github.com/woobat/paintshop.git
```

- Build and install

```
mvn clean install
```

- Build executable jar

```
mvn clean package
```

- Code coverage report 
    
    - go to `target/target/site/jacoco`
    
    - open index.html in a browser

Run
===
To run this application use the following command line options:

```
bash-3.2$ java -jar target/paintshop-1.0-SNAPSHOT.jar --help
Options category 'misc':
  --[no]help [-h] (a boolean; default: "true")
    Prints usage info.

Options category 'startup':
  --file_path [-f] (a string; default: "")
    The input file full path.
  --strategy [-s] (a string; default: "BACKTRACKING_WITH_FORWARD_CHECKING")
    options - NAIVE_SEARCH, BACKTRACKING_WITH_FORWARD_CHECKING, 
    BACKTRACKING_WITH_FORWARD_CHECKING_AND_MRV.
```

Example:

```
bash-3.2$ java -jar target/paintshop-1.0-SNAPSHOT.jar --file_path /Users/coding/my_works/Repos/paintshop/text1.file --strategy BACKTRACKING_WITH_FORWARD_CHECKING
Solving problem at /Users/coding/my_works/Repos/paintshop/text1.file using strategy BACKTRACKING_WITH_FORWARD_CHECKING...
G M G M G
bash-3.2$
```