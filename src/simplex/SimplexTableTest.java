package simplex;

import org.junit.Test;
import simplex.Expression;

import static org.junit.Assert.*;

public class SimplexTableTest {

	private Expression getDefaultExpression() throws Exception {
		int objective = Expression.OBJECTIVE_MAX;
		int[] objectiveFunction = {80, 60};
		int[][] constraints = {{4, 6}, {4, 2}, {0, 1}};
		int[] constraintSigns = {Expression.CONSTRAINT_SIGN_GT, Expression.CONSTRAINT_SIGN_LT, Expression.CONSTRAINT_SIGN_LT};
		int[] b = {24, 16, 3};

		return new Expression(objective, objectiveFunction, constraints, constraintSigns, b);
	}
	
	@Test
	public void constructor() throws Exception {

		Expression e = getDefaultExpression();
		SimplexTable st = new SimplexTable(e);
		Cell[][] table = st.getSimplexTable();
		assertEquals(4, table.length);
		assertEquals(3, table[0].length);

		// objective function
		assertEquals(0, table[0][0].getTop(), 1);
		assertEquals(80, table[0][1].getTop(), 1);
		assertEquals(60, table[0][2].getTop(), 1);
		// first constraint
		assertEquals(-24, table[1][0].getTop(), 1);
		assertEquals(-4, table[1][1].getTop(), 1);
		assertEquals(-6, table[1][2].getTop(), 1);
		// second constraint
		assertEquals(16, table[2][0].getTop(), 1);
		assertEquals(4, table[2][1].getTop(), 1);
		assertEquals(2, table[2][2].getTop(), 1);
		// third constraint
		assertEquals(3, table[3][0].getTop(), 1);
		assertEquals(0, table[3][1].getTop(), 1);
		assertEquals(1, table[3][2].getTop(), 1);
	}

	@Test
	public void changeAlgorithm() throws Exception {

		Expression e = getDefaultExpression();
		SimplexTable st = new SimplexTable(e);
		st.changeAlgorithm(2, 1);

		Cell[][] table = st.getSimplexTable();
		assertEquals(4, table.length);
		assertEquals(3, table[0].length);

		// objective function
		assertEquals(-320, table[0][0].getTop(), 1);
		assertEquals(-20, table[0][1].getTop(), 1);
		assertEquals(20, table[0][2].getTop(), 1);
		// first constraint
		assertEquals(-8, table[1][0].getTop(), 1);
		assertEquals(1, table[1][1].getTop(), 1);
		assertEquals(-4, table[1][2].getTop(), 1);
		// second constraint
		assertEquals(4, table[2][0].getTop(), 1);
		assertEquals(1/4, table[2][1].getTop(), 1);
		assertEquals(1/2, table[2][2].getTop(), 1);
		// third constraint
		assertEquals(3, table[3][0].getTop(), 1);
		assertEquals(0, table[3][1].getTop(), 1);
		assertEquals(1, table[3][2].getTop(), 1);
	}
}