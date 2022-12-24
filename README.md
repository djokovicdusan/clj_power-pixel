# Photo plagiarism detector and classifier tool in Clojure

A simple project designed to classify photos by various metadata tags,
as well as to use OpenCV library to detect plagiarism among photos.
## Prerequisites
You will need [Leiningen](https://leiningen.org/) installed.

## Usage
To start the application, open the terminal and run:
`lein run`

## Description
When the program runs, the user has an option to choose source directory of the photos, as well as
the target directory where photos will be classified by Caption, Artist or Camera make model.
Furthermore, user has an option to choose whether to run a plagiarism check. If one decides
to run a plagiarism check, that will result in creating a unique report of the matched pairs that
can be found in `reports` folder in project root directory.

The business logic for extracting metadata from photos was completed with
the help of [MetadataExtractor](https://github.com/drewnoakes/metadata-extractor) library.

The business logic for plagiarism detection was developed using OpenCV template matching functions.
The method that was used to compare these photos is called 
TM-CCOEFF-NORMED - correlation coefficient method.
TM_CCOEFF method is simply used to make the template and image zero mean and
make the dark parts of the image negative values and the bright parts of
the image positive values. This means that when bright parts of the template
and image overlap you'll get a positive value in the dot product, as well as when dark parts overlap with dark parts.

The UI of this project was developed using [Seesaw](https://github.com/clj-commons/seesaw) library.

This project was developed for the purpose of the course "Tools and Methods of Soft. Eng. and AI" at
Master studies at University of Belgrade, Faculty of Organizational Sciences.

## Testing
[Midje](https://github.com/marick/Midje) library has been used for unit testing. 

## Benchmarking

I compared performance of `map` and `pmap` functions in this project. 
The conclusion is 
that `pmap` outperforms `map` in the context where those were faced.


## Sources
[1] Introduction to OpenCV-Python tutorials (https://docs.opencv.org/3.4/d0/de3/tutorial_py_intro.html)

[2] Howse J., 2013, OpenCV Computer Vision with Python, Packt publishing

[3] Kaehler A., 2017, Learning OpenCV 3: Computer Vision in C++ with the OpenCV Library, O'Rilley

[4] https://github.com/drewnoakes/metadata-extractor

[5] Seesaw REPL Tutorial (https://ericnormand.me/article/seesaw-repl-tutorial)

[6] https://cljdoc.org/d/seesaw/seesaw/1.5.0/api/seesaw.core#button-group

[7] https://github.com/marick/Midje

## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
