package com.gem.gemgridimagesearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import com.loopj.android.image.SmartImageView;

import android.content.Context;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {
// make class for array adapter - make sure is a subclass of android.widget.arrayAdapter<ImageResults> type.
//creat a constructor. context for xml to translat into an item and collectino of items


	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_results, images);  //our custom eview, so get rid of android.R just R.
		// simple_list_item_1 = text view, but call two string method... meh? results in html text being in grid form
		//  -- so want to create own xml layout file (not an activty xml)
		// now tranlate data from 2string to view  - override getview method.
	}
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// get from source -> override in top file menu. convert view to save memory - consever and reuse ie for scroll.
			//if not converView first time make, creat itme, otherwise reuse view
			
			ImageResult imageInfo = this.getItem(position);  // get our data
			SmartImageView ivImage;
			if (convertView ==null) {
				LayoutInflater inflator = LayoutInflater.from(getContext());
				ivImage = (SmartImageView) inflator.inflate(R.layout.item_image_results,parent,false);
			} else {
				ivImage = (SmartImageView) convertView;  // recycles the view
				ivImage.setImageResource(android.R.color.transparent);  //clears the data that is there
				
			}
			ivImage.setImageUrl(imageInfo.getThumbUrl());  //turns string data to image - thumb
			return ivImage;
	
		}
	}
	
	
