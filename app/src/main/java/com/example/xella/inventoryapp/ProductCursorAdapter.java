package com.example.xella.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xella.inventoryapp.data.ProductContract.ProductEntry;

public class ProductCursorAdapter extends CursorAdapter {

    private CatalogActivity catalogActivity = new CatalogActivity();

    /**
     * Constructs a new {@link ProductCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data
     */
    public ProductCursorAdapter(CatalogActivity context, Cursor c) {
        super(context, c, 0);
        this.catalogActivity = context;
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context  app context
     * @param cursor   The cursor from thich to get the data. The cursor is already moved
     *                 to the correct position.
     * @param parent   The parent to which the new view is attached to
     * @return  the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        TextView supplierTextView = (TextView) view.findViewById(R.id.supplier);
        ImageView productImageView = (ImageView) view.findViewById(R.id.image);
        Button saleButton = (Button) view.findViewById(R.id.sale_btn);

        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_IMAGE_URI);
        int supplierColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_SUPPLIER);

        // Extract properties from cursor
        String productName = cursor.getString(nameColumnIndex);
        int productPrice = cursor.getInt(priceColumnIndex);
        final int productQuantity = cursor.getInt(quantityColumnIndex);
        String productImageUri = cursor.getString(imageColumnIndex);
        String productSupplier = cursor.getString(supplierColumnIndex);

        // Populate fields with extracted properties
        nameTextView.setText(productName);
        priceTextView.setText(String.valueOf(productPrice));
        quantityTextView.setText(String.valueOf(productQuantity));
        supplierTextView.setText(productSupplier);

        final long id = cursor.getLong(cursor.getColumnIndex(ProductEntry._ID));

        Uri selectedImage = Uri.parse(productImageUri);
        try {
            productImageView.setImageURI(selectedImage);
        } catch (Exception e) {
            Log.i("ProductCursorAdapter", "Photo insertion failed. Selected image URI: " + selectedImage);
        }

        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogActivity.onSaleButtonClick(id, productQuantity);
                notifyDataSetChanged();
            }
        });
    }
}
