# FaunaTailer

This project was developped using Java.

## Description

This is a Java program that  allows rangers to register and report about animals, their location and their sightings in the forest.
## Author
 Odile Uwimpuhwe

## Live Site

(https://faunatailing.herokuapp.com)


## Installation / Setup instruction
* Open Terminal {Ctrl+Alt+T}

* git clone  ```https://github.com/Odile078/FaunaTailer.git```

* cd FaunaTailer

* code . or atom . based on the text editor you have.
* Fork this repo
* Open Terminal {Ctrl+Alt+T}
* Navigate to appropriate directory using the cd command : cd FaunaTailer
* git clone ```https://github.com/Odile078/FaunaTailer.git```

## Database setup

* CREATE DATABASE Faunatailer;;

* \c Faunatailer;

* CREATE TABLE reganimals(id serial PRIMARY KEY, name varchar,type varchar, health varchar,age varchar);
                
* CREATE TABLE reglocations(id serial PRIMARY KEY, name varchar);

* CREATE TABLE regrangers(id serial PRIMARY KEY, name varchar, badge_id varchar,phone varchar);

* CREATE TABLE regsightings(id serial PRIMARY KEY, reganimal_id int,regranger_id int , reglocation_id int,registered timestamp);

* CREATE TABLE reglocations_regsightings(id serial PRIMARY KEY,reglocation_id int , regsighting_id int);

*  CREATE TABLE regrangers_regsightings(id serial PRIMARY KEY,regranger_id int , regsighting_id int);

*CREATE DATABASE faunatailer_test WITH TEMPLATE faunatailer;


## Technologies Used

* Java
* markdown
* Handlebars
* psql


## Contact Information 

If you have any question or contributions, please email me at [ouwimpuhwe620@daviscollege.com]

## License

MIT License

Copyright (c) 2020 Odile Uwimpuhwe

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
