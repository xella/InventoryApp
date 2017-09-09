# InventoryApp

Inventory app is a project for Android Basics Nanodegree by Google and Udacity

The app contains a list of current products and a button to add a new product.

Each list item displays the product name, current quantity, and price. 
Each list item also contains a Sale Button that reduces the quantity by one (include logic so that no negative quantities are displayed).

When there is no information to display in the database, the layout displays a TextView with instructions on how to populate the database.

User input is validated. In particular, empty product information is not accepted. 
If user inputs product information (quantity, price, name, image), instead of erroring out, 
the app includes logic to validate that no null values are accepted. If a null value is inputted, 
add a Toast that prompts the user to input the correct information before they can continue.

The ‘Contact supplier’ button sends an intent to either a phone app or an email app to contact the supplier using the information stored in the database.
