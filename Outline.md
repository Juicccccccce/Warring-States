# Outline 

## Reminders
1. Every time you make changes to the code (including pulling from upstream) and commit and push to origin, emails about the changes will be sent to all developers of the group.
2. Before modifying anything, pull from origin first to avoid merge conflicts.
3. If you have finished a task, make sure to close the issue in the issues section and underline the task completed below.
4. If you can come up with better implementations for the tasks, feel free to modify this file and commit.
5. Modify this file in a word document. Gitlab loses your unsaved work.
6. If you think your code needs to be cleaned up, mention it in the commit message or open an issue.
7. Add comments to explain what your code does.

## Design

## Tasks
Below are the planned implementations of each task and reasons for specific steps and conditions. The tasks are categorized in order in accordance to location. The steps are simplified for the time being. It will be modified in the future by adding details about the steps. 

Variable names are underlined. 
Types are in bold.
Methods/Instances are italic
Completed tasks will have a dashed line underneath

WarringStatesGame
Task 2 (isCardPlacementWellFormed)
Create an array of char from the cardPlacement input
Define variable a as of type int containing values of the addition the second element of the array with the first element of the array (first subtracted by char ‘a’)
Define variable ascii  as of type int containing the value of the ascii representation of the third element of the array
Check length of cardPlacement (if is equivalent to 3, proceed, otherwise return false)
Check if second element of array is a numeric value 
Check if ascii representation of third element in array is in between 65 and 90 (‘A’ to ‘Z’) or 48 and 57 (0 to 9)
Check if first element of array is in between char ‘a’ and ‘g’ (kingdoms) or char ‘z’ (Zhang Yi)
If it’s in between ‘a’ and ‘g’, check if variable a is in between 0 and 7 (highest number of cards available)
If it’s is equivalent to ‘z’, check if variable a is equivalent to 9 (Zhang Yi)

Task 3 (isPlacementWellFormed)
Check if placement is divisible by 3 and it’s length shorter than or equivalent to 108
Split placement into array of array of char (Larger array contains all card placements represented by three characters, smaller array represent individula card placements, i.e. {{a,0,1},{b,2,A}, ...})
Use is cardPlacementWellFormed on array of char representing card placements. Use a loop to traverse through all the cardPlacements in the array
Check for card duplicates (array should not have cards where the first two characters are the same)
Check if the third element of the card Placement arrays are unique (Location should not have more than one card)

Task 5 (isMoveLegal)
create arrays for each column and row (i.e.{4,5,6,7,8,9}, {4,Y,S,M,G,A})
Split placement into array similar to Task 3
Check if locationChar is equivalent to third element of any card placement array
Check if locationChar is in same column or row array as third character of Zhang Yi’s card
Check if the the row or column contains same first two characters of the cardPlacement before or after locationChar

Task 6 (isMoveSequenceValid)

Task 7 (getSupporters)

Task 8 (getFlags)

Task 10 (generateMove)

Game
Task 9

Task 11

Task 12

Viewer
Task 4 (makePlacement)		


## Contacts
Chan Tze Hsern (0481954596 u6124186@anu.edu.au)
Zipri Ye (0404134972 u5735283@anu.edu.au)
Xinli Xu(0451637153 u6105656@anu.edu.au)