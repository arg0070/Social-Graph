# Social Graph 1.0

Autors: [Alejandro Revilla Gistaín](mailto:arg0070@alu.ubu.es) and 
[Iván Ros Santaolalla](mailto:irs0013@alu.ubu.es)

Social Graph is a project created for the subject of "New technologies and business" 
on the [University of Burgos](http://www.ubu.es)

## What does it do?

Starting on a specific subreddit from [http://www.reddit.com](http://www.reddit.com)
scans all the links that point from this subreddit to another subreddits, 
saving the results to generate two different files:
* nodes.csv: contains all the pages visited.
* edges.csv: contains the relations between nodes.

This files can be imported with a graph generation program like
[Gephi](http://www.gephi.org) to show the final graph.


## Installation

This is a Maven project. Import it as Maven Project on Eclipse.

## Run 

Run the App.java class including as first argument the link to the subreddit
to start with. Ej: [http://www.reddit.com/r/sports](http://www.reddit.com/r/sports).

## Know issues

This isn't a perfect program... Use it on your own risk.
* Links to subsections inside a subreddit are considered as independent nodes.
* Duplicated nodes can appear. 

## Versions

* 1.0: First version.