package com.shop.domains.products;


import com.shop.domains.userItems.UserItemEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

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
    private Timestamp created;
    private Timestamp updated;
    private String category;

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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
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
                Objects.equals(created, entity.created) &&
                Objects.equals(updated, entity.updated) &&
                Objects.equals(category, entity.category) &&
                Objects.equals(items, entity.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, discount, description, sku, created, updated, category, items);
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", category='" + category + '\'' +
                '}';
    }
}