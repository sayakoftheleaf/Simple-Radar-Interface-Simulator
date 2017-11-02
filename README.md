# Simple Radar Interface Simulator

A Simple GUI showing a three randomly generated ships (circles on the map) moving aroung a map in randomly generated directions, and a user navigable ship (triangle on the map) that moves around the map based on user selected directions at the control panel. The program supports three states - the beginning state during which none of the buttons except for "Start" works (this happens when the program is fired up for the first time and when the user hits "Reset"), the running state when all the buttons are active (both on the control panel at the bottom and the output panel to the right) and the user can navigate and pause the state (the program cannot be reset in this state), and the paused state when the navigation systems and the animation is frozen (the program can can be resumed by hitting Start or can be Reset. The output panel to the right is also usable at this state). The positions of any of the ships can be printed to the java console at any point in time using the various buttons at the output panel.

Originally created as a class assignment for COMP86 (Graphical User Interface with OOP) for Tufts University (Fall 2017, Taught by Professor Robert Jacob)

Language = Java
(It's an eclipse project, so eclipse is necessary to clone. Otherwise, individual .java files can be copied and compiled)

Time spent = 28 hours

# Presently implemented:

- Working random position generator for a fixed number of nearby ships
- Working GUI interface
- Working Start/Stop/Reset states (Can Stop only when Started, Can Reset only when Started and then Stopped. Cannot Reset when game is still running, or when game hasn't started yet)
- Error Dialog working when user tries to Reset in running state
- Implemented randomized movements of the Ships
- Implemented animations for the Ships (all ships move 2 frames/100 millisecond).
- Implemented live directional navigation
- Navigable ship head direction changes when going in the desired direction
- Collisions handled for the randomized ships

# Known Bugs
- Ships move out of the map, but are still visible
- Ships keep moving when the disappear from the visible map
- Ships cross through each others paths
- Output Button print the positions of Ships even the user hasn't clicked "Start" and the ships aren't visible on screen
- When collisions are detected, ships will sometimes wiggle in position for a period of time after starting to move again

# Future TODOs : 

- Implement navigation using arrow keys
- Implement relative movement of the Ships in relation to the Control Ship
- Implement collision warning
- Implement more directions?

# Inheritance hierarchy :

- Main inherits from JFrame
- LocationButton inherits from JButton
- NewCanvas inherits from JPanel
- PaintButton inherits from JButton
- Ships (does not inherit from any superclass)

# Aggregation hierarchy :

- Ships has objects of :
		- Point
- NewCanvas has objects of :
		- Point
		- Ships (User-defined)
		- JComboBox
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


