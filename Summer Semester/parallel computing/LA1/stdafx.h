#pragma once


#include <stdio.h>



// TODO: reference additional headers your program requires here
void initMatrix(double** mat, int n);
void initVector(double* vec, int n);

double* mvpl(double* mat, double* vec, int n);
double* mvp2(double** mat, double* vec, int n);
void displayMatrix(double* result, double** mat, double* vec, int n);
void display1DMatrix(double* result, double *mat, double* vec, int n);

void freeMat(double** matr, double* mat, double *result, double *vec);
