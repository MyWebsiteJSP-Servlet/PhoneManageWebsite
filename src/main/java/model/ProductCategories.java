package model;

public class ProductCategories {
private String productCategoriesID;
private String nameCategories;
public ProductCategories(String productCategoriesID, String nameCategories) {
	this.productCategoriesID = productCategoriesID;
	this.nameCategories = nameCategories;
}
public ProductCategories() {
	super();
}
public String getProductCategoriesID() {
	return productCategoriesID;
}
public void setProductCategoriesID(String productCategoriesID) {
	this.productCategoriesID = productCategoriesID;
}
public String getNameCategories() {
	return nameCategories;
}
public void setNameCategories(String nameCategories) {
	this.nameCategories = nameCategories;
}


}
