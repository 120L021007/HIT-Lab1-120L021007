package P1;

import java.io.*;

public class MagicSquare {

	public static void main(String[] args) {
		boolean a, b, c, d, e, f, g;
		a = isLegalMagicSquare("src/P1/txt/1.txt");
		b = isLegalMagicSquare("src/P1/txt/2.txt");
		c = isLegalMagicSquare("src/P1/txt/3.txt");
		d = isLegalMagicSquare("src/P1/txt/4.txt");
		e = isLegalMagicSquare("src/P1/txt/5.txt");
		f = generateMagicSquare(9);
		g = isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(e);
		System.out.println(f);
		System.out.println(g);
	}

	public static boolean isLegalMagicSquare(String fileName) {
		int row = 0;
		File file = new File(fileName);
		try {
			// calculate the number of rows
			BufferedReader bReader1 = new BufferedReader(new FileReader(file));
			String temp = null;
			while ((temp = bReader1.readLine()) != null)
				row = row + 1;
			temp=null;
			bReader1.close();
			
			//check the row
			BufferedReader bReader2 = new BufferedReader(new FileReader(file));
			int[][] myarr = new int[row][row];
			temp = bReader2.readLine();
			String[] rowReader = temp.split("\t");
			int col = rowReader.length;
			if (col != row) {
				if (col == 1) {
					System.out.println(fileName+" SYNTAX ERROR!");
				} else
					System.out.println(fileName+" NOT MATRIX!");
				bReader2.close();
				return false;
			}
			temp = null;
			rowReader = null;
			bReader2.close();
			
			//check every col and transfer to int
			BufferedReader bReader3=new BufferedReader(new FileReader(file));
			int i=0;
			while((temp=bReader3.readLine())!=null) {
				rowReader=temp.split("\t");
				if (rowReader.length != col) {
					System.out.println(fileName+" NOT MATRIX!");
					return false;
				}
				for (int j = 0; j < row; j++) {
					if (rowReader[j].contains(".") || rowReader[j].contains("-")) {
						System.out.println(fileName+" THE MATRIX CONTAINS ILLEGAL NUMBER!");
						return false;
					}
					myarr[i][j] = Integer.parseInt(rowReader[j]);
				}
				i = i + 1;
			}
			bReader3.close();
			
			//calculate the sum
			int rowsum[] = new int[row];
			int colsum[] = new int[col];
			int diagsum[] = new int[2];
			for (i = 0; i < row; i++) 
				for (int j = 0; j < col; j++) 
					rowsum[i] = rowsum[i] + myarr[i][j];
			for (i = 0; i < col; i++) 
				for (int j = 0; j < row; j++) 
					colsum[i] = colsum[i] + myarr[j][i];
			for (i = 0; i < row; i++) {
				diagsum[0] = diagsum[0] + myarr[i][i];
				diagsum[1] = diagsum[1] + myarr[i][row - 1 - i];
			}
			for (i = 0; i < row - 1; i++) 
				if (rowsum[i] != rowsum[i + 1]) 
					return false;
			for (i = 0; i < col - 1; i++)
				if (colsum[i] != colsum[i + 1]) 
					return false;
			if (diagsum[0] != diagsum[1]) 
				return false;
			if (rowsum[0] != colsum[0] || rowsum[0] != diagsum[0] || colsum[0] != diagsum[0]) 
				return false;
		} catch (Exception e) {
			System.out.println("file error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean generateMagicSquare(int n) {
		int magic[][] = new int[n][n];//新建数组，用来存放矩阵元素
		int row = 0, col = n / 2, i, j, square = n * n;//row是行位置，col是列位置，square是元素总个数

		for (i = 1; i <= square; i++) //i从1到square,循环赋值
		{
			magic[row][col] = i;//给row行col列位置元素赋值为i
			if (i % n == 0)//若已赋值n的倍数，下次赋值下一行
				row++;
			else {
				if (row == 0)//若刚赋值的元素是第0行元素，接下来赋值第n-1行
					row = n - 1;
				else
					row--;//否则赋值上一行
				if (col == (n - 1))//若刚赋值n-1列元素，则下次赋值第0行
					col = 0;
				else
					col++;//否则，下次赋值右一列
			}
		}

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++)
				System.out.print(magic[i][j] + "\t");//输出矩阵，元素之间用\t隔开
			System.out.println();//输出回车
		}

		return true;
	}
}
