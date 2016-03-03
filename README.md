#heartbeat

[![Build Status](https://travis-ci.org/serayuzgur/heartbeat.svg?branch=master)](https://travis-ci.org/serayuzgur/heartbeat)
[![codecov.io](https://codecov.io/github/serayuzgur/heartbeat/coverage.svg?branch=master)](https://codecov.io/github/serayuzgur/heartbeat?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/56d59bc80a4ec126f7f860ac/badge.svg)](https://www.versioneye.com/user/projects/56d59bc80a4ec126f7f860ac)
[![Gitter](https://badges.gitter.im/serayuzgur/heartbeat.svg)](https://gitter.im/serayuzgur/heartbeat?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
![codecov.io](https://codecov.io/github/serayuzgur/heartbeat/branch.svg?branch=master)
## Synopsis

heartbeat is a minimalist Java framework for single-board computers (C.H.I.P., Raspberry Pi, BeagleBone, etc.)

**SBC** = Single-Board Computer

## Code Example

All code examples are detailed under module itself. Please take a look at README of each module.
> TODO: An example of HeartbeatApplication

[CheatSheet](./CheatSheet.md)

##Motivation

**SBCs**  are different from traditional computers and need different best practices.  Google developed **Android APIs** with this motivation.
After some programming experience with SBCs, we decided to create a **framework** to solve common development problems by aiding **developers** with **best practices** and **minimalist libraries**.

## Installation
Required Java Version: 1.7

```
git clone https://github.com/serayuzgur/heartbeat.git
cd heartbeat
mvn package
```

## API Reference
### heartbeat-common [Developing]
heartbeat-common is a minimalist Java common library for SBCs.  It includes everyday, best-practice classes and methods for SBCs.
[for more...](./heartbeat-common/README.md)
### heartbeat-pin [Developing]
heartbeat-pin is a minimalist Java pin access layer for SBCs.
[for more...](./heartbeat-pin/README.md)
### heartbeat-network [Developing]
heartbeat-network is a Java network access library for SBCs.  It supports UDP, TCP, and HTTP/S protocols for easy usage. 
[for more...](./heartbeat-network/README.md)
### heartbeat-log [Developing]
heartbeat-log is a light-weight Java logging library for SBCs.
It's usage is very similar to log4j. Appenders and Log formatting wil be implemented later. 
[for more...](./heartbeat-log/README.md)
### heartbeat-app [Waiting]
heartbeat-app is a skeleton Java application for SBC's.
It includes all heartbeat libraries with basic usage scenarios.
[for more...](./heartbeat-app/README.md)
## Tests
All builds tested on 
```
jdk:
  - oraclejdk7
  - openjdk7
```

For running tests:
```
mvn test
```

## Contributors
Please clone or fork the project and create a pull request with at least one commit. You can add your notes on the pull request or share with me via twitter @serayuzgur. Later, we will have a quick chat and welcome you to the team. [![Gitter](https://badges.gitter.im/serayuzgur/heartbeat.svg)](https://gitter.im/serayuzgur/heartbeat?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

## License
The MIT License (MIT)
Copyright (c) 2016 Seray Uzgur

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
