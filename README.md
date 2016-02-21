#heartbeat
[![codecov.io](https://codecov.io/github/serayuzgur/heartbeat/coverage.svg?branch=master)](https://codecov.io/github/serayuzgur/heartbeat?branch=master)
## Synopsis

heartbeat is a minimalist java framework for single-board computers. (CHIP, raspberrypi, beaglebone etc.)

**SBC** = Single Board Computer

## Code Example

>TODO: An example of HeartbeatApplication

[CheatSheet](./CheatSheet.md)

##Motivation

**SBCs**  are different from traditional computers and needs different best-practices. Google developed **Android APIs** with this motivation.
After some programming experience with SBCs we decided to create a **framework** solves common development problems by aiding **developer** with the **best-practices** and **minimalist libraries**.

## Installation

>TODO: Provide code examples and explanations of how to get the project.

## API Reference
### heartbeat-common 
heartbeat-common is a minimalist java common library for SBCs.It includes every day ,best-practice classes and methods for SBCs.
[for more...](./heartbeat-common/README.md)
### heartbeat-pin
heartbeat-pin is a minimalist java pin access layer for SBCs.
[for more...](./heartbeat-pin/README.md)
### heartbeat-network
heartbeat-network is a minimalist java network access library for SBCs.It supports UDP,TCP and HTTP/S protocols for easy usage. 
[for more...](./heartbeat-network/README.md)
### heartbeat-log
heartbeat-log is a minimalist java logging library for SBCs.
It supports file and console and monitor (node) appenders as a really optimized and small library.
[for more...](./heartbeat-log/README.md)
### heartbeat-app
heartbeat-app is a skeleton java application for SBC's.
It includes all heartbeat libraries with a basic usage scenarios.
[for more...](./heartbeat-app/README.md)
## Tests

>TODO: Describe and show how to run the tests with code examples.

## Contributors
Please clone or fork the project and create a push request with at least one commit. You can add your notes on the pull request or share with me from twitter @serayuzgur. Later we will have a quick chat and welcome to the team.

## License
The MIT License (MIT)
Copyright (c) 2016 Seray Uzgur

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.