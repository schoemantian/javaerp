package co.elsumo.werp.model;

import javax.persistence.*;
 
@Entity
public class UnitOfMeasure extends Identifiable { 

@Column(length=50)
private String description;
public void setDescription(String description) {
this.description = description;
}
public String getDescription() {
return description;
}
}