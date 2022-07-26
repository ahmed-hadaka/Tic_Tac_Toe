package projects;

import java.util.Scanner;

public class tic_tac_toe {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 0 empty, 1 = x, 2 = o
		int[][] grid = new int[100][100];

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				grid[i][j] = 0;
			}
		}

		System.out.println("Enter grid dimension: ");
		int n = sc.nextInt();

		/*
		 * We can write length code to verify N row, N columns and 2 diagonals
		 * 
		 * Notice: the behavior of all of them is SAME E.g. We have some starting point
		 * (e.g. 0 0) and we need to verify its row
		 * 
		 * We can use a direction-array style to write an elegant code
		 * 
		 * We will create a few arrays with the 2N+2 needed verifications For every
		 * verification we need 4 values: The starting point (r, c): we need the
		 * starting of each row (N), col (N), 2 Diagonals The direction to move in it
		 * for N steps
		 * 
		 * For example, for the starting (0, 0) To verify its row, we need direction (1,
		 * 0) To verify its col, we need direction (0, 1) To verify its diagonal, we
		 * need direction (1, 1) To verify the right diagonal: we start from the last
		 * cell in first row (0, n-1) and moves (1, -1) 1 means move to next row. -1
		 * means move to the previous column
		 * 
		 * Once done: we iterate over all such start/direction. Loop n times to verify
		 * they all same play symbol
		 */

		int[] rs, cs, dr, dc;
		rs = new int[100];
		cs = new int[100];
		dr = new int[100];
		dc = new int[100];

		int verify = 0;

		// Add row n positions to verify
		for (int r = 0; r < n; r++) {
			rs[verify] = r;
			cs[verify] = 0;
			dr[verify] = 0;
			dc[verify++] = 1;
		}

		// Add col n positions to verify
		for (int c = 0; c < n; c++) {
			rs[verify] = 0;
			cs[verify] = c;
			dr[verify] = 1;
			dc[verify++] = 0;
		}

		// Add diagonal 1
		rs[verify] = 0;
		cs[verify] = 0;
		dr[verify] = 1;
		dc[verify++] = 1;
		// Add diagonal 2
		rs[verify] = 0;
		cs[verify] = n - 1;
		dr[verify] = 1;
		dc[verify++] = -1;

		int turn = 0; // 0 for x, 1 for o. Don't get confused with grid values

		int steps = 0;
		while (true) {
			if (steps == n * n) {
				System.out.println("Tie\n");
				break;
			}
			char symbol = 'x';
			if (turn == 1)
				symbol = 'o';
			System.out.println("Player " + symbol + " turn. Enter empty location (r, c): ");
			int r, c;
			r = sc.nextInt();
			c = sc.nextInt();

			r -= 1;
			c -= 1;

			if (r < 0 || r >= n || c < 0 || c >= n || grid[r][c] != 0) {
				System.out.println("Invalid input. Try again\n");
				continue;
			}
			grid[r][c] = turn + 1;

			// print the grid
			for (int row = 0; row < n; row++) {
				for (int col = 0; col < n; col++) {
					char ch = '.';
					if (grid[row][col] == 1)
						ch = 'x';
					else if (grid[row][col] == 2)
						ch = 'o';
					System.out.print(ch);
				}
				System.out.println();
			}

			// Check win status
			for (int check = 0; check < verify; check++) {
				int row = rs[check], col = cs[check], rd = dr[check], cd = dc[check];
				int cnt = 0, first = grid[row][col];

				if (first == 0)
					continue;

				for (int step = 0; step < n; ++step, row += rd, col += cd) {
					if (grid[row][col] == first)
						cnt++;
				}

				if (cnt == n) {
					System.out.println("Player " + symbol + " won");
					return;
				}
			}

			if (turn == 0) // 0 be 1, 1 be 0
				turn = 1;
			else
				turn = 0;
			steps++;
		}

	}

}
