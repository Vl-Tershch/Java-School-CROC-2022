package ru.croc.task17.shop.objects;

public class Product {
    private Integer id;
    private String arcticle;
    private String name;
    private Integer price;

    public Product(Integer id, String arcticle, String name, Integer price) {
        this.id = id;
        this.arcticle = arcticle;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArcticle() {
        return arcticle;
    }

    public void setArcticle(String arcticle) {
        this.arcticle = arcticle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) {
            return false;
        }

        if (!id.equals(product.id)) return false;
        if (!arcticle.equals(product.arcticle)) return false;
        if (!name.equals(product.name)) return false;
        return price.equals(product.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + arcticle.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", arcticle='" + arcticle + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
