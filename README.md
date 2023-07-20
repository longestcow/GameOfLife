# omogodot's Game Of Life
this project is a little spin on Conway's Game of Life   
it's a cellular automaton and each cell follows rules to decide whether it is alive or dead   
in this project, aka omogodot's GoL, instead of a cell being either dead(0) or alive(1), each cell can be either one of 10 different states, ranging from 0-9.   
if above 4, that cell is considered alive. instead of dying due to the rules, the state is decremented by 1 (capped at 0) every time it is supposed to "die".   
when a cell is supposed to live, its state is incremented by 1 (capped at 9)  
  
another feature in this version of the Game of Life is that you can toggle on/off the overpopulation and underpopulation rules.  
if turned off, these rules would not be taken into account when considering whether a cell state is to be decremented or incremented.   
this gives rise to cool patterns like the ones below -  
(underpopulation being toggled off gives rise to a pattern with one cell gaps between the alive cells)  
![image](https://github.com/longestcow/GameOfLife/assets/83398131/76957346-8845-4538-9a91-25269340722d)  
![image](https://github.com/longestcow/GameOfLife/assets/83398131/84bf7844-d1ec-46ce-bf49-8ad20d8ea215)  
   
(overpopulation being toggled off kind of looks like an island generator)  
![image](https://github.com/longestcow/GameOfLife/assets/83398131/bebb20e4-8a63-4680-b3ef-1b74dd70efc3)  
  
# rules  
1. Any live cell with fewer than two live neighbours dies, as if by underpopulation.   
2. Any live cell with two or three live neighbours lives on to the next generation.  
3. Any live cell with more than three live neighbours dies, as if by overpopulation.  
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.  
(in this project's context, "death" means the state being decremented by 1, while becoming a "live" cell means the state being incremented by 1)
  
# controls
use space to toggle between paused and playing (you can only draw when the game is paused)  
press b or d to draw alive cells on the board    
press e to erase cells (set state to 0)  
press o to toggle overpopulation rule (set to true as default)  
press u to toggle underpopulation rule (set to true as default)  

[link to main code file](https://github.com/longestcow/GameOfLife/blob/main/src/GoL.java)  
[link to download](https://github.com/longestcow/GameOfLife/releases/tag/v1)



