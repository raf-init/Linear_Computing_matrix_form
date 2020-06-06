# LinearComputing_matrix_form
This algorithm was created in order to convert a linear program (imported in a specific form) to the following form:

max/min c^Tx

subject to Ax <=,>= or = b

and x>=0

The final form will be used to create its dual linear program in another algorithm.
Steps followed:
- Reading of the input.txt file
- Finding min/max
- Placing all the coefficients in the same array named table and the variables to be determined in a different one
- Constructing the C table from the coefficients of the array named table
- Constructing the A and B tables in the same way
- Reading and printing of the constraints. Check for errors.

Form of the imported linear program:

max 1 x1 + 3 x2 + 5 x4 + 18 x5

4 x1 + 7 x2  + 0 x4 <= 0

5 x1 + 12 x2 + 1 x4 + 10 x5 >= -100

19 x1 + 100 x4 - 15 x5 = 12

xi>=0 i= 1 2 4 5

**Important note #1:
In case of an x variable missing a coefficient, the user can either use the number zero or just ignore it.
In case of an x variable having the coefficient 1, the number 1 must not be skipped. 
The user doesn't have to enter the x variables in order. 
The constraints have to follow this pattern:
xi>=0 i= 1 2 3

**Important note #2:
For the legitimacy of the code, the list of the following unexpected actions were considered:
 Placing something not like x(pointer), number, (+,-), min or max on the first line
 Placing <=, =, >= two times and/or on the same line
 Not using space in cases such as <=3 (correct form: <= 3)
 Using the same variable twice
 Errors like 2x2 instead of 2 x2
 Errors like 2 + 2 + 1 x1
 Missing a coefficient from a variable
 Errors like + + 3 x1

