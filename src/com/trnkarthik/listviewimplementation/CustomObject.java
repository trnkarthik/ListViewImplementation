package com.trnkarthik.listviewimplementation;

import android.graphics.drawable.Drawable;

public class CustomObject {

	String name;
	Drawable resourceid;
	
	public CustomObject(String name, Drawable sampleImage) {
		this.name = name;
		this.resourceid = sampleImage;
	}
	
	// overriding tostring method
	
	@Override
	public String toString() {
 		return this.name+"";
	}
	
	//getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Drawable getResourceid() {
		return resourceid;
	}

	public void setResourceid(Drawable resourceid) {
		this.resourceid = resourceid;
	}

	
}
