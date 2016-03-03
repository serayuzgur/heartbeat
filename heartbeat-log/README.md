#heartbeat-log

## Synopsis

heartbeat-log is a minimalist java logging library for SBCs. It aims to have a very small codebase with a very light runtime performance impact.


## Code Example
```java
    // TAG gives information about your class
    String TAG = YourClass.class.getName();
    // Log message at trace level.
    Logger.trace(TAG, message); 
    // Output will be "TRACE [2016-03-13 11:50:32,231] com.sample.YourClass: Test Log"
    
    // Log message at trace level with additional parameters and formatting.
    Logger.trace(TAG, "Test Log %s %s", "1", "2"); 
    // Output will be "TRACE [2016-03-13 11:50:32,231] com.sample.YourClass: Test Log 1 2"
```
## Installation
Currently all installation is managed by parent POM.
>TODO: Add maven dependency info.

## API Reference
### Log Level
It supports 5 level of logging which are located in `com.heartbeat.log.Logger.Level`;

* TRACE
* DEBUG
* INFO
* WARNING
* ERROR 

Changing log level is very easy `Logger.setLevel(Logger.Level.DEBUG)`.
>TODO: For now it only supports single log level for all classes and packages. Levelling acording to the tag pattern will be implemented later.

### Log Methods
All methods logs the given message with the level which specified by the method name. All level checks are done at the start of methods. For the ones who gets `Object... args` as parameters it uses `String.format`. If log level is higher then the methods level formatting newer happpens. Basically no performance penalty.

 * trace(String tag, String message)
 * trace(String tag, String message, Object... args)
 * debug(String tag, String message)
 * debug(String tag, String message, Object... args)
 * info(String tag, String message)
 * info(String tag, String message, Object... args)
 * warn(String tag, String message)
 * warn(String tag, String message, Object... args)
 * error(String tag, String message)
 * error(String tag, String message, Object... args)
 * error(String tag, String message, Exception ex)
 * error(String tag, String message, Exception ex, Object... args)
 
### Log Format
At simplest it creates a log with one fixed format. All additional parameters can be given by using the `String.format` style.
`DEBUG_LEVEL  [YYYY-mm-DD hh:MM:ss,SSS] TAG: Message`

>TODO: For now it only supports single log format. Additional log formats will be available if needed.

### Log Appenders
By calling `Logger.setStream(PrintStream stream)` method you can set your own `PrintStream` implementations. By default it uses `System.out` Currently we don't have any managed Appender classes which will be easy to use.  


## Tests
Currently all testing is managed by parent POM.


## License
The MIT License (MIT)
Copyright (c) 2016 Seray Uzgur

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.