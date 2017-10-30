# Simple Radar Interface Simulator

Originally created as a class assignment for COMP86 (Graphical User Interface with OOP) for Tufts University (Fall 2017, Taught by Professor Robert Jacob)

Language = Java
(It's an eclipse project, so eclipse is necessary to clone. Otherwise, individual .java files can be copied and compiled)

Time spent = 25 hours (not completed)

Presently implemented:

- Working random position generator for a fixed number of nearby ships
- Working GUI interface
- Working Start/Stop/Reset states (Can Stop only when Started, Can Reset only when Started and then Stopped. Cannot Reset when game is still running, or when game hasn't started yet)
- Implemented randomized movements of the Ships
- Implemented animations for the Ships (all ships move 2 frames/100 millisecond).

#Known Bugs
- Ships move out of the map, but are still visible
- Ships cross through each others paths
- Live navigation is not working, so the user controlled ship only moves in the direction it started out with
- New Directions have to be implemented by choosing the new direction and then using "Reset".


#TODO : 

- Implement directional navigation
- Implement relative movement of the Ships in relation to the Control Ship
- Implement automatic potential collision detector for the main Ship
- Implement collision warning
- Implement auto-collision avoid for the randomized Ships
- Implement more directions?
