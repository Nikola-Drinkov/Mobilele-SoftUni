package spring.mobilele.services;

import spring.mobilele.models.dtos.AddOfferDTO;
import spring.mobilele.models.dtos.OfferDetailsDTO;
import spring.mobilele.models.entities.Offer;

public interface OfferService {
    public Long saveOffer(AddOfferDTO addOfferDTO);
    public OfferDetailsDTO getOfferDetails(Long id);
}
