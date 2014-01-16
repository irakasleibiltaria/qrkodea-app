package com.lzz.barkodea10;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class inzidentziak extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inzidentzia);
		
		TextView txtProduct = (TextView) findViewById(R.id.textView1);
		

        Intent i = getIntent();
        // getting attached intent data
        String product = i.getStringExtra("product");
        // displaying selected product name
        txtProduct.setText(product);
		
	}
	
	public void gorde (View view) {
        // show content in a popup

	}

}
