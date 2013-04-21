# obj-hierarchy

A toy library to explore the class hierarchy of an object.

## Usage

```clojure
(use '(obj-hierarchy core format))

(-> "something"
    class-hierarchy
    transform
    format-hierarchy)
```

## License

Copyright © 2013 Manuel Paccagnella

Distributed under the Eclipse Public License, the same as Clojure.
