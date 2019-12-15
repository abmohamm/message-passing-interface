# message-passing-interface : This algorithm helps us to perform image analysis,as image is represented in the form of rows and columns.

Requirement : Application is responsible to perform multiplcation on two matrices.

Steps :

1.Code will read first matrix.

2.Once it is read, it will prepare matrix dimension(Number of rows and columns present in first matrix).

3.Then it will read second matrix.

4.Once it is read, it will prepare matrix dimension(Number of rows and columns present in second matrix).

5.After matrix dimension is prepared, matrix multiplication should be performed.In-order to multiply two matrices, below condition 
should be met.

----->Number of columns present in first matrix should be equal to number of rows present in second matrix.Then resultant matrix will be 
      having a dimension(Number of rows in first matrix * Number of columns in second matrix).

6.Application checks for above condtion, if it satisfied one two dimensional-array will be prepared with help of rows in first
matrix and columns in second matrix to hold result of both matrices multiplication.

7.Once the resultant matrix is prepared, multiplication will be performed on both matrices to store result into newly created array.

8.After result is determined, it will be stored in a specific file at one location("c:/temp/") in local machine where one can check file 
with name("samplefile.txt").
