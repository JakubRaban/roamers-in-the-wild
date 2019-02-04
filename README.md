# Roamers in the Wild

This is a simulator of wildlife wandering in the world, trying to get some food and reproduce. Every game is different, as animals are assigned different genes (which determine the probability of moving in each direction) at the start of each game and plants are spawned in random spots. Basic statistics about time elapsed and population of animals and plants can also be found.

## Rules of the simulation

* At the beginning there is one animal with 1000 health (or energy) points
* Each turn every animal which has above 200 HP reproduces, giving half of its energy away to the child (standard male-female reproduction to be implemented)
* While reproducing, baby's genes have substantial chance to slightly differ from the adult's genes
* Animals move once a turn in a direction determined by their genes. While moving, they lose one HP
* Animals eat a plant once they find it, gaining 80 HP
* Animals with 0 HP die
* Stepping outside the map, the animal is moved at the other side of it, as if the map was folded
* The map is a 100x30 rectangle, and in its middle there is a 10x10 square called jungle. Each turn there is 50% chance of spawning a plant inside a jungle and 100% chance of it outside the jungle. Plants grow more densely in the jungle anyway as it is substantially smaller than the area outside it.

## Influencing the simulation

For now we do not have many tools to change the game course. We can use the input field at the bottom of the window (**there exists a bug where it may appear only after resizing the window**) to introduce some basic commands:
* ***delay** value* - sets delay in milliseconds between days. Use *delay 0* for maximum speed
* ***restart*** - speaks for itself
* ***retry*** - spawn a new animal with 1000 HP at a random spot. Helps animals to survive or to repopulate the map in a rare case where all animals die.

Changing default HP values for animals and plants or throwing meteors at the map yet to be implemented.

## Created with

### Tools
* [Gradle](http://gradle.org) - build tool
* [IntelliJ IDEA](http://jetbrains.com/idea) - IDE

### Language features
* Lists, sets, maps and their ~~*ConcurrentModif...*~~ modification-safe methods
* Nested classes
* *Cloneable* interface
* Some fancy randomness-related methods from *RandomnessHandler* class - my first wise use of static methods (except *main*) I can think about.
* "Swing"

## Possible improvements
* Set of animals is OK in the case where animals don't need a partner to reproduce, so we iterate all over the set. When it comes to implementing *true* reproduction things get worse as we would have to iterate over the list twice or do some other things to find animals which are close to each other. *Map\<Position, List\<Animal\>\>* seems to be a better solution.
* *Animal* class mixes usage of setters and direct assignments, which is to be cleaned up
* Swing is never all right
