package com.har_asha.linearalgebra;

import android.view.ViewGroup;

import java.util.Scanner;

public class Matrix {

	private int[] size = new int[2];
	private double[][] matrix = null;
	private Scanner sc;
	
	public Matrix(int m , int n) {
		size[0] = m;
		size[1] = n;
		matrix = new double[m][n];
		sc = new Scanner(System.in);
	}

	public Matrix(double[][] mat , int m , int n) {
        size[0] = m;
        size[1] = n;
        matrix = new double[m][n];
        for(int i=0;i<m;i++) {
            for(int j=0;j<n;j++) {
                matrix[i][j] = mat[i][j];
            }
        }
    }
	
	public Matrix(int n) {
		size[0] = size[1] = n;
		matrix = new double[n][n];
		sc = new Scanner(System.in);
	}
	
	public void enterMatrix() {
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[1] && sc.hasNext();j++) {
				matrix[i][j] = sc.nextDouble();
			}
		}
	}
	
	public Matrix addMatrix(Matrix m2) {
		if(size[0] != m2.size[0] || size[1] != m2.size[1]) {
			System.out.println("Matrices can't be added!!");
			return null;
		}
		Matrix result = new Matrix(size[0] , size[1]);
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[1];j++) {
				result.matrix[i][j] = matrix[i][j] + m2.matrix[i][j];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("For Square matrix press 1\nFor Rectangular matrix press 2...");
		int ch;
		ch = sc.nextInt();
		Matrix obj = null;
		switch(ch) {
		case 1:
			System.out.print("Enter the order of matrix...");
			int order = sc.nextInt();
			obj = new Matrix(order);
			break;
		case 2:
			System.out.print("Enter the number of rows : ");
			int rows = sc.nextInt();
			System.out.print("Enter the number of columns : ");
			int cols = sc.nextInt();
			obj = new Matrix(rows , cols);
			
			break;
		default:
			System.out.print("Invalid Choice...");
			sc.close();
			return;
				
		}
		System.out.print("Enter matrix \n");
		obj.enterMatrix();
		System.out.println("Enter matrix 2 of same order\n");
		Matrix obj2 = new Matrix(obj.size[0] , obj.size[1]);
		obj2.enterMatrix();
		Matrix result = obj.addMatrix(obj2);
		System.out.println("Matrix1 : \n" + obj + "Matrix2 : \n" + obj2 + "Sum of two matrices \n" + result);
		System.out.println("Transpose of a matrix \n" + obj.transpose());
		System.out.println("Inverse of matrix \n" + obj + " is \n" + obj.inverse());
		System.out.println("Product of matrix and its inverse is \n" + obj.multiplication(obj.inverse()));
		System.out.println("Determinant of matrix \n" + obj + " is " + obj.determinant());
		sc.close();
	}
	
	public Matrix transpose() {
		Matrix result = new Matrix(size[1] , size[0]);
		for(int i=0;i<size[1];i++) {
			for(int j=0;j<size[0];j++) {
				result.matrix[i][j] = matrix[j][i];
			}
		}
		return result;
	}
	
	public Matrix multiplication(Matrix mat) {
		if(size[1] != mat.size[0]) {
			return null;
		}
		Matrix result = new Matrix(size[0] , mat.size[1]);
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<mat.size[1];j++) {
				double sum = 0;
				for(int k=0;k<size[1];k++) {
					sum += matrix[i][k]*mat.matrix[k][j];
				}
				result.matrix[i][j] = sum;
			}
		}
		return result;
	}
	
	public static Matrix getIdentityMatrix(int order) {
		Matrix identity = new Matrix(order);
		for(int i=0;i<order;i++) {
			for(int j=0;j<order;j++) {
				identity.matrix[i][j] = 0;
			}
			identity.matrix[i][i] = 1;
		}
		return identity;
	}
	
	public Matrix inverse() {
		if(size[0]!=size[1]) {
			return null;
		}
		Matrix inverse = Matrix.getIdentityMatrix(size[0]);
		double[][] tempMatrix = new double[size[0]][size[0]];
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[0];j++) {
				tempMatrix[i][j] = matrix[i][j];
			}
		}
		for(int i=0;i<size[0];i++) {
			if(tempMatrix[i][i]==0) {
				int j;
				for(j=i+1;j<size[0];j++) {
					if(tempMatrix[j][i] != 0) {
						for(int k=0;k<size[0];k++) {
							double temp = tempMatrix[j][k];
							tempMatrix[j][k] = tempMatrix[i][k];
							tempMatrix[i][k] = temp;
							temp = inverse.matrix[i][k];
							inverse.matrix[i][k] = inverse.matrix[j][k];
							inverse.matrix[j][k] = temp;
							
						}
						break;
					}
					
				}
				if(j==size[0]) {
					//because of all the enteries in the column is 0 so matrix is not invertible
					return null;
				}
			}
			double pivot = tempMatrix[i][i];
			for(int j=0;j<size[0];j++) {
				tempMatrix[i][j] /= pivot;
				inverse.matrix[i][j] /= pivot;
			}
			for(int j=0;j<size[0];j++) {
				if(i==j)
					continue;
				double mul = tempMatrix[j][i];
				for(int k=0;k<size[0];k++) {
					tempMatrix[j][k] = tempMatrix[j][k] - mul*tempMatrix[i][k];
					inverse.matrix[j][k] = inverse.matrix[j][k] - mul*inverse.matrix[i][k];
				}
				//System.out.println(this + " \t " + inverse);
			}
		}
		return inverse;
	}

	private double calculateDeterminant(Matrix matrix) {
		if(matrix.size[1] == 1)
			return matrix.matrix[0][0];
		if(matrix.size[1] == 2)
			return matrix.matrix[0][0]*matrix.matrix[1][1] - matrix.matrix[1][0]*matrix.matrix[0][1];
		else {
			double determinant = 0;
			int multiplier = 1;
			for(int i=0;i<matrix.size[0];i++) {
				Matrix minor = new Matrix(matrix.size[0]-1);
				int r = 0;
				for(int j=1;j<matrix.size[0];j++,r++) {
					int c=0;
					for(int k=0;k<matrix.size[0];k++) {
						if(i==k)
							continue;
						minor.matrix[r][c++] = matrix.matrix[j][k];
					}
				}
				double m0i = calculateDeterminant(minor);
				determinant += multiplier*m0i*matrix.matrix[0][i];
				multiplier *= -1;
			}
			return determinant;
		}

	}
	public Double determinant() {
		Double m , det;
		if(size[0] != size[1]) {
			return null;
		}
		else {
			return calculateDeterminant(this);
		}
			/*m = 1.0; det = 1.0;
			for(int i=0;i<size[0];i++) {
				if(matrix[i][i]==0) {
					int j;
					for(j=i+1;j<size[0];j++) {
						if(matrix[j][i] != 0) {
							for(int k=0;k<size[0];k++) {
								double temp = matrix[j][k];
								matrix[j][k] = matrix[i][k];
								matrix[i][k] = temp;
							}
							break;
						}
						
					}
					if(j==size[0]) {
						//because of all the enteries in the column is 0 so matrix is not invertible
						return 0.0;
					}
					else {
						m *= -1;
					}
				}
				double pivot = matrix[i][i];
				det *= pivot;
				for(int j=0;j<size[0];j++) {
					matrix[i][j] /= pivot;
				}
				for(int j=i+1;j<size[0];j++) {
					if(i==j)
						continue;
					double mul = matrix[j][i];
					for(int k=i;k<size[0];k++) {
						matrix[j][k] = matrix[j][k] - mul*matrix[i][k];
					}
				}
			}
		}
		return det*m; */
	}

	public Matrix getAdjoint() {
		Matrix adjoint = new Matrix(size[0]);
		for(int i=0;i<size[0];i++) {
			for(int j=0;j<size[0];j++) {
				int r=0 , c=0;
				Matrix minor = new Matrix(size[0]-1);
				for(int k=0;k<size[0];k++) {
					if(k == i)
						continue;
					c=0;
					for(int l=0;l<size[0];l++) {
						if(l==j)
							continue;
						minor.matrix[r][c++] = matrix[k][l];
					}
					r++;
				}
				if((j+i) % 2 == 0)
					adjoint.matrix[j][i] = calculateDeterminant(minor);
				else
					adjoint.matrix[j][i] = -calculateDeterminant(minor);
			}
		}
		return adjoint;
	}

    public String toString() {
        String matrixString = "";
        for(int i=0;i<size[0];i++) {
            for(int j=0;j<size[1];j++) {
                matrixString = matrixString + '\\' + String.valueOf(matrix[i][j]) ;
                if(j < size[1]-1)
                    matrixString += ',';
            }
            matrixString += ';';
        }
        return matrixString ;
    }
}
