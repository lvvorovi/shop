package com.shop.domains.products;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.shop.domains.userItems.UserItemEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;
    private String description;
    private String sku;
    private String category;
    @CreatedDate
    @Column(updatable = false)
    private Date created;
    @LastModifiedDate
    private Date updated;

    @OneToMany(mappedBy = "product")
    private Set<UserItemEntity> items;

    public ProductEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Set<UserItemEntity> getItems() {
        return items;
    }

    public void setItems(Set<UserItemEntity> items) {
        this.items = items;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity entity = (ProductEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(name, entity.name) &&
                Objects.equals(price, entity.price) &&
                Objects.equals(discount, entity.discount) &&
                Objects.equals(description, entity.description) &&
                Objects.equals(sku, entity.sku) &&
                Objects.equals(category, entity.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount, description, sku, category);
    }
}