package matrix;

/**
 * This class provides a data representation for the AbstractMatrix
 * implementation of the Matrix API.
 * It uses a two-dimensional array of integers to store matrix elements.
 * @author Maxwell Silver Sec 12
 */
public class ArrayImplementation extends AbstractMatrix {
    
    /**
     * Creates an array representation of a matrix of integers.
     * Elements of the array are initialized to zero.
     * @param numRows the number of rows in the matrix
     * @param numColumns the number of columns in the matrix
     */
    public ArrayImplementation (int numRows, int numColumns) {
        super(numRows, numColumns);
        elements = new int[numRows][numColumns];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numColumns; c++) {
                elements[r][c] = 0;
            }
        }
    }

    /**
     * Gets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @return the element at the indicated row and column
     * @throws MatrixException if row or column is out of range
     */
    @Override
    public int get(int row, int column) {
        checkBounds(row, column);
        return elements[row][column]; 
    }

    /**
     * Sets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @param element the value to set in the matrix
     * @throws MatrixException if row or column is out of range
     */
    @Override
    public void set(int row, int column, int element) {
        checkBounds(row, column);
        elements[row][column] = element; 
    }

    /**
     * Adds this matrix to another.
     *
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    @Override
    public Matrix add(Matrix other) {
        Matrix sum = new ArrayImplementation(getNumRows(), getNumColumns());
        add(this, other, sum);
        return sum;
    }

    /**
     * Multiples this matrix by another.
     *
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        Matrix product = new ArrayImplementation(getNumRows(), other.getNumColumns());
        multiply(this, other, product);
        return product;
    }
    
    private final int[][] elements;
    
}