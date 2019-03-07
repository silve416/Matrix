package matrix;

import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 * This class provides a data representation for the AbstractMatrix
 * implementation of the Matrix API.
 * It uses a list of list of integers to store matrix elements.
 * @author Maxwell Silver Sec 12
 */
public class ArrayListImplementation extends AbstractMatrix {

    /**
     * Creates a list representation of a matrix of integers.
     * Elements of the list are initialized to zero.
     * @param numRows the number of rows in the matrix
     * @param numColumns the number of columns in the matrix
     */
    public ArrayListImplementation(int numRows, int numColumns) {
        super(numRows, numColumns);
        elements = new ArrayList<>(numRows);
        
        for (int i=0; i < numRows; i++){
            ArrayList temp = new ArrayList<>(numColumns);
            elements.add(temp);
            
            for (int j=0; j < numColumns; j++){
                temp.add(0);
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
        ArrayList e = (ArrayList) elements.get(row);
        return (int)e.get(column);
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
        ArrayList e = (ArrayList) elements.get(row);
        e.set(column, element);
        elements.set(row, e);
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
       Matrix sum = new ArrayListImplementation(getNumRows(), getNumColumns());
       add(this, other, sum);
        return sum; 
    }

    /**
     * Multiplies this matrix by another.
     *
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        Matrix product = new ArrayListImplementation(getNumRows(), other.getNumColumns());
        multiply(this, other, product);
        return product;
    }
    
    /**
     * Private instance fields follow
     * 
     */

    private final ArrayList elements;
}