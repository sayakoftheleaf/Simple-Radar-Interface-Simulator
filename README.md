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
- Implemented live directional navigation

#Known Bugs
- Ships move out of the map, but are still visible
- Ships keep moving when the disappear from the visible map
- Ships cross through each others paths
- Output Button print the positions of Ships even the user hasn't clicked "Start" and the ships aren't visible on screen

#Future TODOs : 

- Implement relative movement of the Ships in relation to the Control Ship
- Implement automatic potential collision detector for the main Ship
- Implement collision warning
- Implement auto-collision avoid for the randomized Ships
- Implement more directions?

Inheritance hierarchy :

- Main inherits from JFrame
- LocationButton inherits from JButton
- NewCanvas inherits from JPanel
- PaintButton inherits from JButton
- Ships (does not inherit from any superclass)

Aggregation hierarchy :

- Ships has objects of :
		- Point
- NewCanvas has objects of :
		- Point
		- Ships (User-defined)
		- JComboBox
		- Main (User-defined)
		- Graphics
- Main has objects of :
		- NewCanvas (User-defined)
		- PaintButton (User-defined)
		- JLabel
		- Container
		- JComboBox
		- LocationButton (User-defined)
		- JPanel
		- Timer
		- (other temporary objects passed as arguments)
- PaintButton has objects of : 
		- NewCanvas (User-defined)
		- Main (User-defined)
		- JOptionPane
- LocationButton has objects of : 
		- Ships (User-defined)
		- NewCanvas (User-defined)


