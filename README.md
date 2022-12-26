# Photo plagiarism detector and classifier tool in Clojure

A simple project designed to classify photos by various metadata tags,
as well as to use OpenCV library to detect plagiarism among photos.

## Why on Earth would you need this tool?

First of all, I will tell you why I needed it so bad. Whenever I travel
(especially hiking), I take photos both with my old Canon EOS 7D, and
my mobile phone(it can easily replace a DSLR in some situations). Sorting those photos
in meaningfully named folders takes a lot of my time and energy, since they
are somehow strangely named (etc. 12319028391819SDSA.jpg), and eventhough
I switched to "Show big tiles" in my folder preview mode, I cannot
effortlessly differentiate which photos belong to which device.
Same story goes for classifying in Adobe Lightroom (entering letters A, B, C
in Caption field), and for various other data.

Why I added a plagiarism tool?

I thought that the first part of this project was a bit "to easy" to satisfy
my Professor, so
I wanted a further challenge. Even though OpenCV is a popular library
with great documentation and tutorials in Python and C++, it was a tough journey
to put it all in a meaningful context(tons of documentation to read).
The deeper I dived into Clojure, the more I realised I was naming my variables and functions
in a "javaboilerplatecodegenerator" manner. Even though i found
it easier this way, I will try to avoid this practice in
my next projects.

That's it.
Hope you will like it.
Remember, it is my first Clojure project! :)

## Prerequisites

Not much. You will need [Leiningen](https://leiningen.org/) installed.

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
I have tried to implement **TDD** (Test Driven Development) approach during the process of
creating this software tool, by simultaneously creating features and testing them.

## Benchmarking

I did some quick-benching here also.
I compared performance of `map` and `pmap` functions in this project.
The conclusion is
that `pmap` outperforms `map` in the context where those were faced.

## Conclusion - (Opening myself up to anonymous members of GitHub community)

The goal of this project was getting myself familiar with Clojure
programming language, as well as it's libraries and key strength. I did some serious
brainstorming on which real-life problems could my program solve.
Then, I did research
on libraries that could be integrated with Clojure code and
also help me on my way to create a useful program. Then - I got my hands dirty.
The development of this project took way more than I thought it would take (my first estimation was 30 hours),
but I am glad
that it managed to come up as a useful tool.
I am happy that I have found out this cool & powerful programming language.
I most sincerely hope that I will find a way to furthermore enhance
my knowledge of
Clojure, especially in the field of AI, as well as to combine it with Android mobile development (somehow).

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
