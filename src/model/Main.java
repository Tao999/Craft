package model;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Item myItem = new Item(1, "carre de bois", "non");
		Item i2 = new Item(2, "planchette de bois", "non");

		int[][] craft = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				craft[i][j] = i2.getId();
			}
		}
		myItem.setRecipe(craft);
		System.out.println(myItem.toString());
		System.out.println(i2.toString());

		System.out.println("-------");

		Inventory myInv = new Inventory();
		for (int i = 0; i < 2; i++)
			System.out.println(myInv.pushItem(myItem));

		for (int i = -3; i < 3; i++)
			System.out.println(myInv.putItem(i2, i));
		System.out.println(myInv.toString());

		System.out.println("item choisi : " + myInv.pickId(0));
		System.out.println(myInv.toString());

		myInv.eraseInventory();
		System.out.println(myInv.toString());

	}

}
