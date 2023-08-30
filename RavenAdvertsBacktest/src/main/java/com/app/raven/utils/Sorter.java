package com.app.raven.utils;

public class Sorter {
	public String urlSorter(String url) {
		String replacer ="https://drive.google.com/uc?id="; // necessary part of the url
		String detach = url.replace("https://drive.google.com/file/d/",""); // removed the first part of the url which is not necessary
		String[] arrayString = detach.split("/"); // this will give a array of string when we split the remaning url by /
		String id = arrayString[0]; // the first argument is the id of the image
		return (replacer + id);
	}
}
