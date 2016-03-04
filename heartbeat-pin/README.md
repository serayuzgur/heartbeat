#heartbeat-pin

## Synopsis

heartbeat-pin is a minimalist java pin access layer for SBCs.


## Code Example
Basic usage

```java
Pin pin = PinManager.getInstance().createPin(ChipPinCode.XIO_P0,Pin.Mode.IN); 
// You can read pin value only if pin is at IN mode
boolean r = pin.read(); 
//Reads pin value (true = 1, false = 0 )
pin.setMode(Pin.Mode.OUT);
// You can write pin value only if pin is at OUT mode
pin.write(!r);
//Writes pin value (true = 1, false = 0 )
```
Listener

```java
PollerPinListener listener = new PollerPinListener(pin); 
// OR WatcherPinListener listener = new WatcherPinListener(pin);
listener.setChangeListener(new PinChangeListener() {
    PinReader reader = new PinReader(p
    @Override
    public void onChange(Pin pin) {
        try {
            Logger.info(TAG, "Read: %b ", reader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
});
listener.start();
...
...
...
listener.close(); // Do not forget to close after your work finishes.
```

High Speed Read

```java
PinReader reader = new PinReader(pin);
for (int i = 0; i < 5; i++) {
    boolean read = reader.read();
    Logger.info(TAG, "Read: %b ", read);
}
reader.close();
```

High Speed Write

```java
PinWriter writer = null;
try {
    writer = new PinWriter(pin);
} catch (IOException e) {
    e.printStackTrace();
}
for (int i = 0; i < 5; i++) {
    boolean written = i % 2 == 0;
    try {
        writer.write(written);
    } catch (IOException e) {
        e.printStackTrace();
        //TODO: Do something meanigful
    }
}
writer.close();
```
##Motivation

>TODO: Write something about testing problems, abstraction from SBC's

## Installation

>TODO: Provide code examples and explanations of how to get the project.

## API Reference
### Pin
Base class for pins. Pin holds necessary information (code, path) and methods (enable, disable, setMode, getMode, write, read)  for pins.
### PinCode
An interface for pin codes across different boards and operating systems. All implementations must names as Document code and provide OS code and hardware code of the pin. 

* **Document Code:** Code which used to specify pin at the documents. For ex. C.H.I.P uses **XIO\_P0** for **GPIO 0**.
* **Operating System Code:** Code which used to mount pin at the os. For ex. [CHIP-linux](https://github.com/NextThingCo/CHIP-linux) uses **408** for **XIO\_P0**.
* **Hardware Code:** Code which describes position of pin on the board. For ex. **U14\_13**
* **DOC\_CODE(OS\_CODE, HW\_CODE)** For example **XIO\_P0("408", "U14\_13")**

### PinManager
PinManager is an helper class for preventing common mistakes. It helps developer to manage pins without worrying about;

* Multiple Pin initialization
* Using wrong pin codes (different models, manifacturer or OS)
* Wrong mode (in/out) usage for read write operations.
* Multiple listeners and memory leaks

### PinWriter
PinWriter is a performance oriented class for pin value writing operations. It uses FileChannel and ByteBuffers with position resetting for low memory allocation and low latency writing operation.
### PinReader
PinReader is a performance oriented class for pin value reading operations. It uses FileChannel and ByteBuffers with position resetting for low memory allocation and low latency reading operation.
### PinListener
Pin listener is an interface which helps monitoring changes on pin values.
#### PollerPinListener
Pin listener implementation which checks with polling.
#### WatcherPinListener
Pin listener implementation which checks with WatcherService.
### PinCommand
PinCommand is an interface for easy board/os spesific operation commands.`Pin` methods (enable, disable, setMode, getMode, write, read) uses commands to get the job done.
#### SystemCommand
PinCommand implementation which uses system commands like *cat,tee,etc.* to acomplish the task.
#### JavaPinCommand
PinCommand implementation which uses pure Java to acomplish the task.

## Pinouts
### C.H.I.P
![alt text](http://docs.getchip.com/images/chip_pinouts.jpg "C.H.I.P pin layout")

## Tests

>TODO: Describe and show how to run the tests with code examples.

## License
The MIT License (MIT)
Copyright (c) 2016 Seray Uzgur

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.