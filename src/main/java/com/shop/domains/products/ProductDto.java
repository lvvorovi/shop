package com.shop.domains.products;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ProductDto extends RepresentationModel<ProductDto> {

    private Long id;
    @NotNull
    @Size(min = 3, max = 32)
    private String name;
    @NotNull
    @DecimalMin("0")
    private BigDecimal price;
    private String description;
    @Size(max = 10)
    private String sku;
    @DecimalMin("0")
    private BigDecimal discount;
    private String category;
    private BigDecimal actualPrice;
    private Timestamp created;
    private Timestamp updated;

    public ProductDto() {
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

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProductDto dto = (ProductDto) o;
        return Objects.equals(id, dto.id) &&
                Objects.equals(name, dto.name) &&
                Objects.equals(price, dto.price) &&
                Objects.equals(description, dto.description) &&
                Objects.equals(sku, dto.sku) &&
                Objects.equals(discount, dto.discount) &&
                Objects.equals(category, dto.category) &&
                Objects.equals(actualPrice, dto.actualPrice) &&
                Objects.equals(created, dto.created) &&
                Objects.equals(updated, dto.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, price, description, sku, discount, category, actualPrice, created, updated);
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", sku='" + sku + '\'' +
                ", discount=" + discount +
                ", category='" + category + '\'' +
                ", actualPrice=" + actualPrice +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}