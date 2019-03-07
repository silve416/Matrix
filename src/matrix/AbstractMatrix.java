package matrix;

/**
 * This abstract class partially implements the Matrix API.
 * This class should be subclassed to provide a data representation.
 *
 * @author Maxwell Silver Sec 12
 */
public abstract class AbstractMatrix implements Matrix {

    /**
     * Creates a 2D matrix.
     * Only the number of rows and columns are stored. A subclass constructor
     * should create the data structure used to store matrix elements.
     * @param numRows the number of rows in this matrix
     * @param numColumns the number of columns in this matrix
     * @throws MatrixException if numRows or numColumns is not positive
     */
    public AbstractMatrix(int numRows, int numColumns) {
        if (numRows <= 0) {
            throw new MatrixException(String.format("numRows (%s) must be positive", numRows));
        }
        if (numColumns <= 0) {
            throw new MatrixException(String.format("numColumns (%s) must be positive", numColumns));
        }
        this.numRows = numRows;
        this.numColumns = numColumns;
    }

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @Override
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Creates a visual representation of this matrix as a string. This method
     * can be used for debugging. This is a template method; it uses a method
     * (get) that must be implemented by a subclass. This method overrides a
     * method in the Object class.
     *
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                builder.append(String.format("%6s", get(r, c)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    
    /**
     * Checks row and column indices for this matrix.
     * This method can be used by subclasses to signal an error condition if
     * a row or column index is out of bounds.
     * @param row a row index for this matrix
     * @param column a column index for this matrix
     * @throws MatrixException if row or column is out of bounds
     */
    protected void checkBounds(int row, int column) {
        if(row >= numRows || row < 0){
        throw new MatrixException(
            "Row index ("+ row +") out of bounds");
    }
        if(column >= numColumns || column < 0){
            throw new MatrixException(
            "Column index ("+ column +") out of bounds");
        }
    }

    /**
     * Tests for equality of this matrix with another. Matrices are equal if
     * they have the same dimensions and all elements are equal by ==. This is a
     * template method; it uses a method (get) that must be implemented by a
     * subclass. This method overrides a method in the Object class, so it must
     * type check and cast its argument to the correct type.
     *
     * @param obj the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix,
     * <b>false</b> otherwise
     */
    @Override
    public boolean equals(Object obj) {
     Matrix mat = (Matrix)obj;
        if(!( numRows == mat.getNumRows() &&  numColumns == mat.getNumColumns())){
            return false;
        }
        else
        for(int i=0; i < numRows; i++){
            for(int j=0; j < numColumns; j++){
                if (!(get(i,j) == mat.get(i,j)))
                    return false;
            }
        }
        return true;
    }
    
    
        /**
     * Adds two matrices.
     * This is a static method; it works only on its explicit arguments.
     * This method is not part of the Matrix API and is intended to be called
     * by a subclass.
     * @param m1 first matrix operand
     * @param m2 second matrix operand
     * @param sum matrix sum of the operands
     * @throws MatrixException if dimensions of all argument matrices are 
     * not the same
     */
    protected static void add(Matrix m1, Matrix m2, Matrix sum) {
        int m1R = m1.getNumRows();
        int m2R = m2.getNumRows();
        int rSum = sum.getNumRows();
        int m1C = m1.getNumColumns();
        int m2C = m2.getNumColumns();
        int cSum = sum.getNumColumns();
        int t = 0;
        
        if ((m1R != m2R) || (m1R != rSum))
            throw new MatrixException(
            "Matrices are not compatible for adding");
        
        else
            if ((m1C != m2C) || (m1C != cSum))
                throw new MatrixException(
                "Matrices are not compatible for adding");
            
        else
                for(int i=0; i < m1R; i++){
                    for(int j=0; j< m1C; j++){
                        t = m1.get(i,j) + m2.get(i,j);
                        sum.set(i,j,t);
                        }
                }
    }
    /**
     * Multiplies two matrices.
     * This is a static method; it works only on its explicit arguments.
     * This method is not part of the Matrix API and is intended to be called
     * by a subclass.
     * @param m1 first matrix operand
     * @param m2 second matrix operand
     * @param product matrix product of the operands
     * @throws MatrixException if any of the following is true:
     * <ul>
     * <li> the number of columns in first matrix and number of rows in second matrix
     *      are not equal</li>
     * <li> the number of rows in first matrix and product are not equal </li>
     * <li> the number of columns in second matrix and product are not equal</li>
     * </ul>
     * 
     */
    protected static void multiply(Matrix m1, Matrix m2, Matrix product) {
        if(m1.getNumColumns() == m2.getNumRows()){
            int num = 0;
            for(int i=0; i < m2.getNumColumns(); i++){
                for(int j=0; j < m1.getNumRows(); j++){
                    num = multiplyHelper(m1, m2, j, i);
                    product.set(j,i,num);
                }
            }
        }              
        else{
                    throw new MatrixException(String.format(
                            "Matrices are not compatible for multiplying"));
        }
    }
    /**
     * Calculates the product of two matrices.
     * Calculates the product of two matrices at a certain position
     * @param m1 first matrix operand
     * @param m2 second matrix operand
     * @param j row counter
     * @param i column counter
     * @return product of two matrices
     */
    protected static int multiplyHelper(Matrix m1, Matrix m2, int j, int i){
        int num = 0;

        for(int k=0; k < m1.getNumColumns(); k++){
            num = m1.get(j,k) * m2.get(k,i) + num;
        }
        return num;
    }    
    /**
     * Private instance fields follow
     */

    private final int numRows;
    private final int numColumns;

}