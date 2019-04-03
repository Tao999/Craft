package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppBase app = new AppBase();
		int[][] crafto;
		crafto = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				crafto[i][j] = Item.NOT_AN_ITEM;
		crafto[0][1] = 0;
		crafto[1][1] = 0;

		System.out.println(app.craftRecipe(new Craft(crafto)));
	}

}
