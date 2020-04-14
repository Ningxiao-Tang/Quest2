###QuestofLegend
1. game play
In the 8*8 game board, @,#,$ is heroes' position, " is the BUSH cell, · is KOULOU cell, ▲ is CAVE cell, █ is inaccessible.

Please use w/a/s/d to move heroes, enter q to quit, enter i to show player info and enter b to return to the market. 

2.fight implementation details 
dodge chance:= agility/10000;
damage:= strength*0.05;
defense:= defense;
level up: gain 100*monster_level coins and 2 exp;	
before every round, hero recover 1% hp and mana.
If hero lands on Bush cell hero get 10% boost to dexterity
If hero lands on koulou cell hero get 10% boost to strength
If hero lands on cave cell hero get 10% boost to agility

3. Class Descriptions


###Quest
1. game play
The game board is an 8*8 board as shown below, where H is the player, m is the market, █ is an inaccessible cell.
|H|█| | | | |m|m|
| |m| |m|█|█|█|m|
| | |m| | | | | |
|m| |m| | |m| | |
|m|█| |m| | | |█|
|m|m| | | | |m| |
| | |m|m| |m| | |
|m| |m| |m| |m| |
Please use w/a/s/d to move player, enter q to quit, enter i to show player info.

2.fight implementation details
dodge chance:= agility/10000;
damage:= strength*0.05;
defense:= defense;
level up: gain 100*monster_level coins and 2 exp;	
before every round, hero recover 1% hp and mana.
