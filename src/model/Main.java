package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item myItem = new Item(1, "carré de bois", "non");
		Item i2 = new Item(2, "planchette de bois", "non");

		Item[][] craft = new Item[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				craft[i][j] = i2;
			}
		}
		myItem.setRecipe(craft);
		System.out.println(myItem.toString());
		System.out.println(i2.toString());
	}

}
