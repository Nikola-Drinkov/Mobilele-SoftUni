package spring.mobilele.models.dtos;

import jakarta.validation.constraints.*;

public class AddOfferDTO {

    @NotBlank(message = "{add.offer.model}")
    private String model;

    @NotNull(message = "{add.offer.price.not.empty}")
    @Positive(message = "{add.offer.price.positive}")
    private Double price;

    @NotBlank(message = "{add.offer.engine}")
    private String engine;

    @NotBlank(message = "{add.offer.transmission}")
    private String transmission;

    @NotNull(message = "{add.offer.year.not.empty}")
    @Min(1900)
    @Max(2024)
    private Integer year;

    @NotNull(message = "{add.offer.mileage.not.empty}")
    @PositiveOrZero(message = "{add.offer.mileage.positive.or.zero}")
    private Integer mileage;

    @NotBlank(message = "{add.offer.description.not.empty}")
    @Size(min = 5, message = "{add.offer.description.length}")
    private String description;

    @NotBlank(message = "{add.offer.url.not.empty}")
    @Size(min = 10, max = 500, message = "{add.offer.url.size}")
    private String imageUrl;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
