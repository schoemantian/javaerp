package co.elsumo.werp.model;

import java.math.*;

import javax.persistence.*;
import org.openxava.annotations.*;
 
@Entity
@View(members=
"stockControl [" +
"stockCode, productClass, priceGroup, category, subCategory, closed, closedForClearance;" +
"]" +
"specifications [" +
"weight, length, width, depth, photo, morePhotos;" +
"]" +
"data [" +
"description, unitOfMeasure, price;" +
"]" +
"remarks;"
)
public class Product{
//********************************************* Product Stock Code ********************************************* 
	@Id @Column(length=8)
	private String stockCode;

	public String getStockCode() {
	    return stockCode;
	}
	public void setStockCode(String stockCode) {
	    this.stockCode = stockCode;
	}
	
//********************************************* Product Class ********************************************* 
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    private ProductClass productClass;
    
    public ProductClass getProductClass() {
		return productClass;
	}
	public void setProductClass(ProductClass productClass) {
		this.productClass = productClass;
	}
	
//********************************************* Price Group ********************************************* 
    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    private PriceGroup priceGroup;
    
    public PriceGroup getPriceGroup() {
		return priceGroup;
	}
	public void setPriceGroup(PriceGroup priceGroup) {
		this.priceGroup = priceGroup;
	}
    
//******************************************** Product Category ********************************************     
    @ManyToOne(
        fetch=FetchType.LAZY,
        optional=true)
    @DescriptionsList
    private Category category;
    
    public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
//****************************************** Product Sub Category ******************************************     
    @ManyToOne(
        fetch=FetchType.LAZY,
        optional=true)
    @DescriptionsList
    private SubCategory subCategory;
    
    public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
//******************************************** Product Weight ********************************************
		@Column(length=15)
	    private String weight;
	    
	    public String getWeight() {
	        return weight;
	    }
	    public void setWeight(String weight) {
	        this.weight = weight;
	    }
		
//******************************************** Product Length ********************************************
		@Column(length=15)
		  private String length;
		  
		  public String getLength() {
		      return length;
		  }
		  public void setLength(String length) {
		      this.length = length;
		  }
		
//******************************************** Product Width ********************************************
		@Column(length=15)
		  private String width;
		  
		  public String getWidth() {
		      return width;
		  }
		  public void setWidth(String width) {
		      this.width = width;
		  }
		
//******************************************** Product Depth ********************************************
		@Column(length=15)
		  private String depth;
		  
		  public String getDepth() {
		      return depth;
		  }
		  public void setDepth(String depth) {
		      this.depth = depth;
		  }
		  
//****************************************** Stock Description ******************************************  
    @Column(length=50) @Required
    private String description;
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
//******************************************** Unit Of Measure ********************************************     
    @ManyToOne(
        fetch=FetchType.LAZY,
        optional=true)
    @DescriptionsList
    private UnitOfMeasure unitOfMeasure;
    
    public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}
	public void setUnitOfMeasure (UnitOfMeasure unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
    
//********************************************** Stock Price ********************************************** 
    @Stereotype("MONEY")
    private BigDecimal price;
     
    public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
//********************************************** Stock Photo **********************************************
	@Stereotype("PHOTO")
    @Column(length=16777216)
    private byte [] photo;
	
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
//******************************************** Stock Photo More ********************************************
	@Stereotype("IMAGES_GALLERY")
    @Column(length=32)
    private String morePhotos;
	
	public String getMorePhotos() {
		return morePhotos;
	}
	public void setMorePhotos(String morePhotos) {
		this.morePhotos = morePhotos;
	}
	
//****************************************** Stock Specifications ******************************************
	 @Stereotype("MEMO")
	    private String remarks;
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
//************************************************ Closed Validation ****************************************
	private boolean closed;
		public boolean isClosed() {
		return closed;
	}
	
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
//****************************************** Closed For Clearance Validation **********************************
	private boolean closedForClearance;
		public boolean isClosedForClearance() {
		return closedForClearance;
	}
	
	public void setClosedForClearance(boolean closedForClearance) {
		this.closedForClearance = closedForClearance;
	}
	
     
}