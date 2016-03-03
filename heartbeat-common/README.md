#heartbeat-common

## Synopsis

heartbeat-common is a java common library for SBCs.It includes every day, best-practice classes and methods for SBCs.
For all utils our goal was simplicity with high performance and small code base.

## Code Example

>TODO: An example of HeartbeatApplication

## Installation
Currently all installation is managed by parent POM.
>TODO: Add maven dependency info.

## API Reference
This section will only explain main groups of utility classes. Each group is a package. For example for finding Array utilities    please take a look at `com.heartbeat.common.array` package.

* **Array -** Common operations about arrays. All classes inside named strictly to explain the related type. For ex. `byte[]` utils are at `ByteArray` class.
* **Board -** Utility classes which helps to determinate **board manifacturer** and **operating system**. All platform spesific utilities will be placed here. 
* **Cli -** Holds important methods and classes which help to create a good&nice **command line interface**. 
* **Conf -** Necessary infrastructure for applications with configuration.
* **Dir -** Provides directory utilities. For example getting path jar at runtime.
* **File -** Utility classes related to file operations. 
* **Json -** Json utilities based on Boon.


## Tests
Currently all testing is managed by parent POM.


## License
The MIT License (MIT)
Copyright (c) 2016 Seray Uzgur

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.