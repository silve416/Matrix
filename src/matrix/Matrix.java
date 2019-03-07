package matrix;

/**
 * This interface describes an API for working with two-dimensional
 * matrices of integers.
 * @author Maxwell 
 */
public interface Matrix {

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    int getNumRows();

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    int getNumColumns();

    /**
     * Gets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @return the element at the indicated row and column
     * @throws MatrixException if row or column is out of range
     */
    int get(int row, int column);

    /**
     * Sets the element at the indicated row and column in this matrix.
     *
     * @param row the row position for the element.
     * @param column the column position for the element.
     * @param element the value to set in the matrix
     * @throws MatrixException if row or column is out of range
     */
    void set(int row, int column, int element);

    /**
     * Adds this matrix to another.
     *
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    Matrix add(Matrix other);

    /**
     * Multiplies this matrix by another.
     *
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    Matrix multiply(Matrix other);

    /**
     * Adds a row to this matrix. This is a default method; it provides an
     * implementation that can be overridden. This is a template method; it uses 
     * methods (getNumRows, getNumColumns, set) that must be implemented by a subclass.
     *
     * @param row index of row to be added
     * @param values the values of the row to be added
     * @throws MatrixException if row is out of range or there are too many
     * values
     */
    default void addRow(int row, int... values) {
        if (row >= getNumRows() || row < 0) {
            throw new MatrixException(String.format("Row index (%s) out of bounds", row));
        }
        if (values.length > getNumColumns()) {
            throw new MatrixException(String.format("Number of values (%s) exceeds number of columns (%s)", 
                                                    values.length, 
                                                    getNumColumns()));
        }
        int column = 0;
        for (int value : values) {
            set(row, column++, value);
        }
    }
    
}