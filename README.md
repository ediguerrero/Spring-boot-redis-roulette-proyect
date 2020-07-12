# Roulette proyect

This is a minimal proyect were we use spring boot like framework to expose diferents endpoints and redis like database trying to simulate a real casino roulette. All services are exposed in **http://localhost:8080/** .

## How to run 
  - First you need to install the spring boot tool , you can find it at the official page **https://spring.io/tools**.
  - Then you need the redis server, you can download it here **https://github.com/MicrosoftArchive/redis/releases**.
  - Once you have all tools you just need to clone this repository, open it with the spring tool.
  - Run the redis server.
  - Run the proyect.

# How to use

Using postman you can use the diferents endpoints of this proyect:

  - The first one is **/createroulette** this endpoint open a roulette and give you the id of it. You can use this doing a post petition.
  - With a post petition you can use  **/openroulette/{id}** to change the roulette status to open and start to call your lucky for this game. The id is the roulette that you want to open.This will return a message kind of roulette open successfully.
  - Then you finally can to start with a post petition to bet with the end point **/tobet/{rouletteid}/{number}/{amount}**. Using the id of your preferred roulette, your lucky number since 0 to 36 and your amount, here your amount can be only through 10000.
  - Once every body did theirs bets is time to close the roulette, use a post petition with the endpint **/closeroulette/{id}**. Remember use the id of your roulette. This will return all the bets that every body did in the roulette.
  - With a get petition you can see all the roulettes and their status with **/roulettes**.
  - If you want to delete a specific roulette you can use a delete petition with the end point **/roulettes/{id}** .
  
