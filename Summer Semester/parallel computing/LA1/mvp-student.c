// MatrixVectorProduct.cpp : Defines the entry point for the console application.
//
#include <math.h>
#include <stdlib.h>
#include <stdio.h>
void initMatrix(double** mat, int n);
void initVector(double* vec, int n);

double* mvpl(double* mat, double* vec, int n);
double* mvp2(double** mat, double* vec, int n);
void displayMatrix(double* result, double** mat, double* vec, int n);
void display1DMatrix(double* result, double *mat, double* vec, int n);

void freeMat(double** matr, double* mat, double *result, double *vec);
int main(int argc, char *argv[])
{
	//int rows = 0, colums = 0;
	//printf("Please Enter Size of the Matrix of rows and colums by clicking space in between: %d %d", rows, colums);
	//scanf_s("%d%d", &rows, &colums);

	//declaring variable 2d array pointer and 1d array pointer
	double **matr, *mat, *result, *vec;
	int n;

	printf("Please Enter Matrix Size: ");
	scanf("%d", &n);

	//alloctae memory size of pointer array dynamically
	matr = (double**)malloc(n * sizeof(double));
	vec = (double*)malloc(n * sizeof(double));
	mat = (double*)malloc(n * sizeof(double));
	result = (double*)malloc(n * sizeof(double));

	//alloctae memory size of 2d pointer array dynamically
	for (int i = 0; i < n; i++)
	{
		matr[i] = (double *)calloc(n, sizeof(double));
	}


	//function call 
	displayMatrix(result, matr, vec, n);
        printf("\n");
	printf("\n");
	display1DMatrix(result, mat, vec, n);

	freeMat(matr, mat, result, vec);
	system("pause");
	return 0;
}

//dealocate memory or free the memory location
void freeMat(double** matr, double* mat, double *result, double *vec) {

	//dealoucate memory location
        free(matr);
	free(mat);
	free(vec);
	free(result);	
}
//display 2d pointer array init value and vector value and show the result
void displayMatrix(double* result, double** mat, double* vec, int n) {

	//result matrix hold the calculated matrix-vector product
	result = mvp2(mat, vec, n);

	//got through rows and colums of nested loops and display index value of 2d pointer
	for (int i = 0; i < n; i++) {

		for (int j = 0; j < n; j++) {
			printf(" %.2lf ", mat[i][j]);
			if (j == n - 1) {
				printf("   %.2lf ", vec[i]);
				if ( i == n/2) {
					printf(" =");
				}
				printf("    %.2lf ", result[i]);
			}
		}



		printf("\n");
	}
}

//display the matrix and vector and result of matrix-vector product
void display1DMatrix(double* result, double *mat, double* vec, int n) {

	//function call to return result
	result = mvpl(mat, vec, n);
	
	//loops through matrix and display index value
	for (int i = 0; i < n; i++) {

		for (int j = 0; j < n; j++) {
			printf(" %.2lf ", *(mat + i * n + j));
			if (j == n - 1) {
				printf("   %.2lf ", vec[i]);

				if (i == n / 2) {
					printf(" =");
				}
				printf("    %.2lf ", result[i]);
			}
		}

		printf("\n");
	}

}
// calculate matrix-vector product and return result
double* mvpl(double* mat, double* vec, int n) {

	double **matr;
	double* result;
	int k = 0;

	//allocate the memory location
	matr = (double**)malloc(n * sizeof(double));
	result = (double*)malloc(n * sizeof(double));

	for (int i = 0; i < n; i++)
	{
		matr[i] = (double *)calloc(n, sizeof(double));
	}

	//function call to init matrix and vector array with given values
	initMatrix(matr, n);
	initVector(vec, n);

	//assign 2d pointer array value to 1d pointer array 
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			mat[k] = matr[i][j];
			k++;
		}
	}

	/*
		calculate matrix vector product
		calculate matrix vector product
		go thorough each rows and columns and multiplay each columns rows index value with each index of vec
		and add the value to res variable
		in here k has a size of 2d array of rows,
		so thats why i didvide K by n to get the proper size and divide the matrix into k/n pices
	*/
	double es2 = 0.0;
	for (int i = 0; i < n; i++) {
		es2 = 0.0;
		for (int t = 0; t < k / n; t++) {

			es2 += mat[t] * vec[t];
		}
		result[i] = es2;
	}
	return result;
}

//2d array of pointer to calculate matrix-vector product and return result
double* mvp2(double** mat, double* vec, int n) {

	double* result;
	result = (double*)malloc(n * sizeof(double));

	//function call to init matrix and vector
	initMatrix(mat, n);
	initVector(vec, n);


	/*calculate matrix vector product
	 go thorough each rows and columns and multiplay each columns rows index value with each index of vec
	 and add the value to res variable
	 and store res value inside result 1d pointer array
	 after go through all the rows of first columns, it will go back first loops to start secend index of columns
	 and reset the res value to 0, so that it can whole new produts value, and assign to results vector
	 Loops will run untill it finish looping through all columns
	*/
	double res = 0;
	for (int columns = 0; columns < n; columns++) {
		res = 0;
		for (int rows = 0; rows < n; rows++) {
			res += mat[columns][rows] * vec[rows];

		}
		result[columns] = res;
	}
	return result;
}


//initialize 2d pointer matrix with value
void initMatrix(double** mat, int n) {

	//it will loops through  n time rows and columns and assign given value to matrix
	for (int i = 0; i < n; i++) {

		for (int j = 0; j < n; j++) {


			if (j == i) {
				mat[i][j] = 2;
			}
			else {
				mat[i][j] = 1;
			}
		}
	}

}

//initialize 1d pointer array with vector value
void initVector(double* vec, int n) {

	for (int i = 0; i < n; i++) {

		vec[i] = 1;
	}
}
