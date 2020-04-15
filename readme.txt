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
a. Armory Class - The armor class was designed create the Heros' Armor and relay any valuable information about it. This includes information such as the augment to the Hero and the base statistics of the armor.
b. Board Class - The Board class allows the user to generate a board based on the their desired size, but in this scenario the class is more receptive to boards that are 8x8 or larger. Also it creates the obstacles that the Role class intereacts with.
c. Color Class - This class is crated to distinguish different spots on the board. 
d. Cell Class - The Cell class is used for each individual piece of the board. This allows for manipulation of the board and its contents.
e. Dragons Class - This creates the Dragon subclass of monsters.
f. Equipment Class - The Super class to Armory,Weapons adn the other super class Spell. This creates the add-ons that the Hero class can use; As well as contain the functions that determine if the Hero can access the equipment.
g. Exoskeletons Class - This creates the Exoskeleton subclass of monsters.
h. Hero Class - The Hero class is one of the main types of Characters. This Class creates and facilitates the spread of information abput the Heros. 
i. HeroSet Class - This class contains the name and information about the available heros that the user can choose from. 
j. IO class - Designed to have fucnctions that are user friendy and user-dependent. 
k. ItemSet Class - This class contains the name and information of all available equipment that the user can purchase and/or use.
l. Main Class - Here is where the user plays the game.
m. Market Class - Here the user is able to see the items and select which ones to use if they have met the appropriate conditions.
n. Monsters Class - One of the sub-classes of Role. Here is where the information of a Monster is created displayed and what it can do.
o. MonsterSet class - This class contains the name and information of all possible monsters that can appear.
p. Paladin Class - creates the subclass of Heros: Paladins.
q. Player Class - This class creates a visble representation of the user on the board.
r. Potions Class - This class is designed to create and dictate the actions a hero is able to do with a potion.
s. Quest Class - The class where all the other classes are used in conjunction to create the actual game. The procedure in which the classes are used is located here.
t. Role Class - This is the Super Class of Hero and Monsters. Contains the basic information related to the creation.
u. RoleSet Class- An abstract class designed to so that specific classes contain the list() function.
w. Spell Class - A super class that creates the basic information of spells and is in charge damage and effects.
x. Spirits Class - This creates the spirit subclass of monsters.
y. Sorcerers Class - This creates the Sorcerers subclass of heros. 
z. Tile Class - This creates the Tiles that are used to create the board. This allows the board to have tiles that can be interacted with.
aa. TileSet Class - Contains the specific types of tiles that can appear on the board.
ab. Warriors Class - This creates the Warrior subclass of heroes.
ac. Weapon Class - The Weapon Class is designed to create the Heros' weapon and retrieve the information associated with Heros. This includes augmenting the Hero's stats and dealing the damage.



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
