googolMatrix
============

Possible solutions for the problem shown in "Wonders of Numbers", chapter 23, "Cube labyrinth"

The params needed are the following ones:

-debug|-all:
	Determines if you want to output some debug OR you want to know how many possible routes give a correct answer

'Objective':
	Sets the value that as to be accumulated through the route
	
'Last Position':
	A vector with the position where the route has to finish

'Initial Position': (optional)
	A vector with the position where the route has to begin. It's set to zeroes if not informed

'Max Size':
	A vector with the size of each dimension

'Value List':
	The list with the values of each node of the multimensional matrix

example params:

-all 202 2,0,0 3,3,3 21,20,19,24,23,22,27,26,25,12,11,10,15,14,13,18,17,16,3,2,1,6,5,4,9,8,7

	gets all the posible routes,
	that gets to 202 value,
	ending in the 2,0,0 position,
	beginning in the 0,0,0 positions (default value because is not informed),
	the matrix has 3 dimensions of 3 nodes each,
	and the values of the dimensions are the ones described in the last param 
	
	
The code was made with Netbeans some years ago. Contact me at gbmaiz@gmail.com if you have any doubts and we'll try to guess what I made back then
	