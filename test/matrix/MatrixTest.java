package matrix;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class MatrixTest {

    public MatrixTest() {
//       m = new ArrayImplementation(4, 3);
//       m1 = new ArrayImplementation(4, 3);
//       m2 = new ArrayImplementation(3, 3);
//       m3 = new ArrayImplementation(4, 4);
//       m4 = new ArrayImplementation(4, 3); 
//       m5 = new ArrayImplementation(4, 3);
//       m6 = new ArrayImplementation(4, 3);
//       m7 = new ArrayImplementation(3, 2);
//       m8 = new ArrayImplementation(4, 2);
        
         m = new ArrayListImplementation(4, 3);
         m1 = new ArrayListImplementation(4, 3);
         m2 = new ArrayListImplementation(3, 3); 
         m3 = new ArrayListImplementation(4, 4); 
         m4 = new ArrayListImplementation(4, 3); 
         m5 = new ArrayListImplementation(4, 3);
         m6 = new ArrayListImplementation(4, 3); 
         m7 = new ArrayListImplementation(3, 2);
         m8 = new ArrayListImplementation(4, 2);

        m1.addRow(0, 3, 215, -57);
        m1.addRow(1, -12, 4, 369);
        m1.addRow(2, 16, -34, 117);
        m1.addRow(3, -21, -6, 64);
        
        m2.addRow(0, 3, 215, -57);  // m1, m2 have different row dimensions
        m2.addRow(1, -12, 4, 369);
        m2.addRow(2, 16, -34, 117);
        
        m3.addRow(0, 3, 215, -57, 1); // m1, m3 have different column dimensions
        m3.addRow(1, -12, 4, 369, 2);
        m3.addRow(2, 16, -34, 117, 3);
        m3.addRow(3, -21, -6, 64, 5);
        
        m4.addRow(0, 3, 215, -57);  // m1.equals(m4)
        m4.addRow(1, -12, 4, 369);
        m4.addRow(2, 16, -34, 117);
        m4.addRow(3, -21, -6, 64);
        
        m5.addRow(0, 6, 430, -114);  // !m1.equals(m5)
        m5.addRow(1, -24, 8, 738);  // m5.equals(m1.add(m4))
        m5.addRow(2, 32, -68, 234);
        m5.addRow(3, -42, -12, 128); 
        
        m6.addRow(0, 3, 2, 5);
        m6.addRow(1, 2, 4, 3);
        m6.addRow(2, 6, 5, 1);
        m6.addRow(3, 1, 6, 4);
        
        m7.addRow(0, 2, 3);      // m6.multiply(m7) is legal
        m7.addRow(1, 4, 2);      // m7.multiply(m6) is not legal
        m7.addRow(2, 3, 4);
        
        m8.addRow(0, 29, 33);    // m8.equals(m6.multiply(m7))
        m8.addRow(1, 29, 26);
        m8.addRow(2, 35, 32);
        m8.addRow(3, 38, 31);
    }

    @Test
    public void testConstructor() {  // m is a 4x3 matrix
        assertTrue(m.getNumRows() == 4);
        assertTrue(m.getNumColumns() == 3);
    }
    
    @Test
    public void testBoundsForGet() {  // m is a 4x3 matrix
        try {
            m.get(-1, 2);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (-1) out of bounds"));
        }
        
        try {
            m.get(4, 2);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (4) out of bounds"));
        }
        
        try {
            m.get(0, -1);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Column index (-1) out of bounds"));
        }
        
        try {
            m.get(0, 3);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Column index (3) out of bounds"));
        }
    }
    
    @Test
    public void testBoundsForSet() {  // m is a 4x3 matrix
        try {
            m.set(-1, 2, 17);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (-1) out of bounds"));
        }
        
        try {
            m.set(4, 2, 17);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (4) out of bounds"));
        }
        
        try {
            m.set(0, -1, 17);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Column index (-1) out of bounds"));
        }
        
        try {
            m.set(0, 3, 17);
            fail("get should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Column index (3) out of bounds"));
        }
    }
    
    @Test
    public void testGet() {  // m is a 4x3 matrix
        StringBuilder builder = new StringBuilder();
        String fmt = "%6s%6s%6s\n";
        builder.append(String.format(fmt, 0, 0, 0));
        builder.append(String.format(fmt, 0, 0, 0));
        builder.append(String.format(fmt, 0, 0, 0));
        builder.append(String.format(fmt, 0, 0, 0));
        
        // toString tests legal calls to get
        assertTrue(m.toString().equals(builder.toString()));  
    }

    @Test
    public void testAddRow() {  // m is a 4x3 matrix
        try {
            m.addRow(-1, 3, 215, -57);
            fail("addRow should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (-1) out of bounds"));
        }
        try {
            m.addRow(4, 3, 215, -57);
            fail("addRow should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Row index (4) out of bounds"));
        }
        try {
            m.addRow(0, 3, 215, -57, 13);
            fail("addRow should not have succeeded");
        }
        catch(MatrixException ex) {
            assertTrue(ex.getMessage().equals("Number of values (4) exceeds number of columns (3)"));
        }
        
        // addRow tests legal calls to set
        m.addRow(0, 3, 215, -57);
        m.addRow(1, -12, 4, 369);
        m.addRow(2, 16, -34, 117);
        m.addRow(3, -21, -6, 64); 
        
        StringBuilder builder = new StringBuilder();
        String fmt = "%6s%6s%6s\n";
        builder.append(String.format(fmt, 3, 215, -57));
        builder.append(String.format(fmt, -12, 4, 369));
        builder.append(String.format(fmt, 16, -34, 117));
        builder.append(String.format(fmt, -21, -6, 64));
        
        assertTrue(m.toString().equals(builder.toString()));
    }

    /**
     * Tests testing matrices for equality.
     * m1 is a 4 x 3 matrix
     * m2 is a 3 x 3 matrix
     * m3 is a 4 x 4 matrix
     * m4 is a 4 x 3 matrix equal to m1
     * m5 is a 4 x 3 matrix not equal to m1
     */
    @Test
    public void testEquals() {
        assertTrue(!m1.equals(m2));
        assertTrue(!m1.equals(m3));
        assertTrue(m1.equals(m4));
        assertTrue(!m1.equals(m5));
    }

    /**
     * This test will not be run for the lab, but will be run for the later assignment.
     * 
     * Tests adding matrices.
     * m1 is a 4 x 3 matrix
     * m2 is a 3 x 3 matrix
     * m3 is a 4 x 4 matrix
     * m4 is a 4 x 3 matrix
     * m5 is a 4 x 3 matrix equal to the sum of m1 and m4
     */
     @Test
     public void testAdd() {
         try {
             m1.add(m2);
             fail("matrix add should not have succeeded");
         }
         catch(MatrixException ex) {
             assertTrue(ex.getMessage().equals("Matrices are not compatible for adding"));
         }
         try {
             m1.add(m3);
             fail("matrix add should not have succeeded");
         }
         catch(MatrixException ex) {
             assertTrue(ex.getMessage().equals("Matrices are not compatible for adding"));
         }
         assertTrue(m5.equals(m1.add(m4)));
         assertFalse(m1.equals(m4.add(m5)));
     }

    /**
     * This test will not be run for the lab, but will be run for the later assignment.
     * 
     * Tests multiplying matrices.
     * m1 is a 4 x 3 matrix
     * m2 is a 3 x 3 matrix
     * m3 is a 4 x 4 matrix
     * m6 is a 4 x 3 matrix
     * m7 is a 3 x 2 matrix
     * m8 is a 4 x 2 matrix equal to the product of m6 and m7
     *
     * This test must attempt the following but fail, catching the appropriate exception: 
     *    m1.multiply(m3)
     *    m3.multiply(m2)
     *    m7.multiply(m6)
     * Then it should test m6.multiply(m7) and find the result equals m8.
     */
     @Test
     public void testMultiply() {

         try {
             m1.multiply(m3);
             fail("matrix add should not have succeeded");
         }
         catch(MatrixException ex) {
             assertTrue(ex.getMessage().equals("Matrices are not compatible for multiplying"));
         }
     
         try {
             m3.multiply(m2);
             fail("matrix add should not have succeeded");
         }
         catch(MatrixException ex) {
             assertTrue(ex.getMessage().equals("Matrices are not compatible for multiplying"));
         }
         try {
             m7.multiply(m6);
             fail("matrix add should not have succeeded");
         }
         catch(MatrixException ex) {
             assertTrue(ex.getMessage().equals("Matrices are not compatible for multiplying"));
         }
         assertTrue(m8.equals(m6.multiply(m7)));
     }
    
    private Matrix m, m1, m2, m3, m4, m5, m6, m7, m8;
    
}